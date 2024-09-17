package com.dinakarans.dns;

import com.dinakarans.dns.section.DnsQuestion;
import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;

public class DnsQuery extends DnsMessage {

    public DnsQuery(byte[] reqBufBytes) {
        super(reqBufBytes);
        init();
    }

    private void init() {
        setQuestions();
    }

    @Override
    void setQuestions() {
        int qdcount = header.getQDCOUNT();
        DnsQuestion question = new DnsQuestion(header.getNextByteBuffer());
        for (int i = 0; i < qdcount; ++i) {
            questions.add(question);
            ByteBuffer questionByteBuffer = question.getByteBuffer();
            String name = DnsUtil.parseName(questionByteBuffer);
            question.setName(name);
            question = new DnsQuestion(question.getNextByteBuffer());
        }
    }

}
