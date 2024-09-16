package com.dinakarans.dns;

import com.dinakarans.dns.section.DnsAnswer;
import com.dinakarans.dns.util.DnsConstants;

public class DnsResponse extends DnsMessage {

    private final DnsQuery query;

    private final DnsAnswer answer;

    public DnsResponse(byte[] respBufBytes, DnsQuery query) {
        super(respBufBytes);
        this.query = query;
        answer = new DnsAnswer(byteBuffer, question);
        init();
    }

    private void init() {
        setHeader();
        setQuestion();
        setAnswer();
    }

    private void setHeader() {
        header.setID(query.header.getID());

        // Mimic OPCODE and RD
        short queryQOATRZR = (short) (0B0_1111_0_0_1_0_000_0000 & query.header.getQOATRZR());
        short queryOPCODE = (short) (0B0_1111_0_0_0_0_000_0000 & queryQOATRZR);
        short rd = (short) (queryOPCODE == 0 ? 0B0_0000_0_0_0_0_000_0000 : 0B0_0000_0_0_0_0_000_0100);
        short qoatrzr = (short) (DnsConstants.DnsHeader.QOATRZR | queryQOATRZR | rd);
        header.setQOATRZR(qoatrzr);
    }

    private void setQuestion() {
        question.setName(query.question.getName());
        question.setType(query.question.getType());
        question.setClass_(query.question.getClass_());

        // Incrementing question count
        header.setQDCOUNT((short) (header.getQDCOUNT() + 1));
    }

    private void setAnswer() {
        answer.setName(query.question.getName());
        answer.setType(query.question.getType());
        answer.setClass_(query.question.getClass_());
        answer.setTTL(DnsConstants.DnsAnswer.TTL);
        answer.setRLength(DnsConstants.DnsAnswer.RDLENGTH);
        answer.setRData(DnsConstants.DnsAnswer.RDATA);

        // Incrementing answer count
        header.setANCOUNT((short) (header.getANCOUNT() + 1));
    }

}
