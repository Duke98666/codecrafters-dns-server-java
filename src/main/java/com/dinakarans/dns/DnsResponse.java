package com.dinakarans.dns;

import com.dinakarans.dns.section.DnsAnswer;
import com.dinakarans.dns.section.DnsQuestion;
import com.dinakarans.dns.util.DnsConstants;

import java.util.ArrayList;
import java.util.List;

public class DnsResponse extends DnsMessage {

    private final DnsQuery query;

    protected final List<DnsAnswer> answers;

    public DnsResponse(byte[] respBufBytes, DnsQuery query) {
        super(respBufBytes);
        this.query = query;
        answers = new ArrayList<>();
        init();
    }

    private void init() {
        setHeader();
        setQuestions();
        setAnswers();
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

    @Override
    void setQuestions() {
        short qdcount = query.header.getQDCOUNT();
        if (qdcount == 0) {
            return;
        }
        header.setQDCOUNT(qdcount);
        DnsQuestion question = new DnsQuestion(header.getNextByteBuffer());
        for (int i = 0; i < qdcount; ++i) {
            questions.add(question);
            DnsQuestion queryQuestion = query.questions.get(i);
            question.setName(queryQuestion.getName());
            question.setType(queryQuestion.getType());
            question.setClass_(queryQuestion.getClass_());
            question = new DnsQuestion(question.getNextByteBuffer());
        }
    }

    private void setAnswers() {
        short ancount = query.header.getQDCOUNT();
        if (ancount == 0) {
            return;
        }
        header.setANCOUNT(ancount);
        DnsAnswer answer = new DnsAnswer(questions.getLast().getNextByteBuffer());
        for (int i = 0; i < ancount; ++i) {
            answers.add(answer);
            DnsQuestion queryQuestion = query.questions.get(i);
            answer.setName(queryQuestion.getName());
            answer.setType(queryQuestion.getType());
            answer.setClass_(queryQuestion.getClass_());
            answer.setTTL(DnsConstants.DnsAnswer.TTL);
            answer.setRDLength(DnsConstants.DnsAnswer.RDLENGTH);
            answer.setRData(DnsConstants.DnsAnswer.RDATA);
            answer = new DnsAnswer(answer.getNextByteBuffer());
        }
    }

}
