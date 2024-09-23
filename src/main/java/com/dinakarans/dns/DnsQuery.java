package com.dinakarans.dns;

import com.dinakarans.dns.section.DnsHeader;
import com.dinakarans.dns.section.DnsQuestion;
import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class DnsQuery {

    private DnsHeader header;

    private List<DnsQuestion> questions;

    public DnsQuery() {
        this.questions = new ArrayList<>();
    }

    public DnsHeader getHeader() {
        return header;
    }

    public void setHeader(DnsHeader header) {
        this.header = header;
    }

    public List<DnsQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<DnsQuestion> questions) {
        this.questions = questions;
    }

    protected ByteBuffer byteBuffer() {
        byte[] bytes = new byte[512];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);
        DnsUtil.writeHeader(byteBuffer, getHeader());
        DnsUtil.writeQuestions(byteBuffer, getQuestions());
        return byteBuffer;
    }

    public byte[] array() {
        return byteBuffer().array();
    }

}
