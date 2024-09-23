package com.dinakarans.dns.util;

import com.dinakarans.dns.DnsQuery;
import com.dinakarans.dns.DnsResponse;
import com.dinakarans.dns.section.DnsAnswer;
import com.dinakarans.dns.section.DnsHeader;
import com.dinakarans.dns.section.DnsQuestion;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public final class DnsUtil {

    private DnsUtil() {
        // Utility class
    }

    public static String parseName(ByteBuffer byteBuffer) {
        StringJoiner name = new StringJoiner(".");
        int position = byteBuffer.position();
        int labelLength;
        boolean compression = false;
        while ((labelLength = byteBuffer.get()) != 0) {
            if ((labelLength & 0B11_00_00_00) == 0B11_00_00_00) {
                compression = true;
                int offset = ((labelLength & 0B00_11_11_11) << 8) | (byteBuffer.get() & 0B11_11_11_11);
                byteBuffer.position(offset);
            } else {
                byte[] label = new byte[labelLength];
                byteBuffer.get(label);
                name.add(new String(label, StandardCharsets.UTF_8));
            }
        }
        if (compression) {
            byteBuffer.position(position);
        }
        return name.toString();
    }

    public static byte[] getNameBytes(String name) {
        String[] labels = name.split("\\.");
        // n labels are separated by (n - 1) dots (.)
        // total content characters (x1) = (name.length() - labels.length + 1)
        // total prefix label length bytes (x2) = labels.length
        // null byte (x3) = 1
        // total = x1 + x2 + x3 = name.length() + 2
        int nameBytesLength = name.length() + 2;
        byte[] nameBytes = new byte[nameBytesLength];
        ByteBuffer nameBuffer = ByteBuffer.wrap(nameBytes);
        for (String label : labels) {
            nameBuffer.put((byte) label.length()).put(label.getBytes(StandardCharsets.UTF_8));
        }
        nameBuffer.put((byte) 0);
        return nameBytes;
    }

    public static DnsQuery readQuery(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);
        DnsQuery query = new DnsQuery();
        query.setHeader(readHeader(byteBuffer));
        query.setQuestions(readQuestions(byteBuffer, query.getHeader().getQdcount()));
        return query;
    }

    public static DnsResponse readResponse(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);
        DnsResponse response = new DnsResponse();
        response.setHeader(readHeader(byteBuffer));
        response.setQuestions(readQuestions(byteBuffer, response.getHeader().getQdcount()));
        response.setAnswers(readAnswers(byteBuffer, response.getHeader().getAncount()));
        return response;
    }

    public static DnsHeader readHeader(ByteBuffer byteBuffer) {
        DnsHeader header = new DnsHeader();
        header.setId(byteBuffer.getShort());
        header.setFlags(byteBuffer.getShort());
        header.setQdcount(byteBuffer.getShort());
        header.setAncount(byteBuffer.getShort());
        header.setNscount(byteBuffer.getShort());
        header.setArcount(byteBuffer.getShort());
        return header;
    }

    public static List<DnsQuestion> readQuestions(ByteBuffer byteBuffer, int qdcount) {
        List<DnsQuestion> questions = new ArrayList<>();
        for (int i = 0; i < qdcount; ++i) {
            DnsQuestion question = new DnsQuestion();
            question.setName(parseName(byteBuffer));
            question.setType(byteBuffer.getShort());
            question.setClazz(byteBuffer.getShort());
            questions.add(question);
        }
        return questions;
    }

    public static List<DnsAnswer> readAnswers(ByteBuffer byteBuffer, int ancount) {
        List<DnsAnswer> answers = new ArrayList<>();
        for (int i = 0; i < ancount; ++i) {
            DnsAnswer answer = new DnsAnswer();
            answer.setName(parseName(byteBuffer));
            answer.setType(byteBuffer.getShort());
            answer.setClazz(byteBuffer.getShort());
            answer.setTtl(byteBuffer.getInt());
            answer.setRdLength(byteBuffer.getShort());
            byte[] rDataBytes = new byte[answer.getRdLength()];
            byteBuffer.get(rDataBytes);
            answer.setRData(new String(rDataBytes, StandardCharsets.UTF_8));
            answers.add(answer);
        }
        return answers;
    }

    public static void writeHeader(ByteBuffer byteBuffer, DnsHeader header) {
        byteBuffer.putShort(header.getId());
        byteBuffer.putShort(header.getFlags());
        byteBuffer.putShort(header.getQdcount());
        byteBuffer.putShort(header.getAncount());
        byteBuffer.putShort(header.getNscount());
        byteBuffer.putShort(header.getArcount());
    }

    public static void writeQuestions(ByteBuffer byteBuffer, List<DnsQuestion> questions) {
        for (DnsQuestion question : questions) {
            byteBuffer.put(getNameBytes(question.getName()));
            byteBuffer.putShort(question.getType());
            byteBuffer.putShort(question.getClazz());
        }
    }

    public static void writeAnswers(ByteBuffer byteBuffer, List<DnsAnswer> answers) {
        for (DnsAnswer answer : answers) {
            byteBuffer.put(getNameBytes(answer.getName()));
            byteBuffer.putShort(answer.getType());
            byteBuffer.putShort(answer.getClazz());
            byteBuffer.putInt(answer.getTtl());
            byteBuffer.putShort(answer.getRdLength());
            byteBuffer.put(answer.getRData().getBytes(StandardCharsets.UTF_8));
        }
    }
}
