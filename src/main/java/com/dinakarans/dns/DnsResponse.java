package com.dinakarans.dns;

import com.dinakarans.dns.section.DnsAnswer;
import com.dinakarans.dns.section.DnsHeader;
import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class DnsResponse extends DnsQuery {

    private List<DnsAnswer> answers;

    public DnsResponse() {
        this.answers = new ArrayList<>();
    }

    public List<DnsAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<DnsAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public void setHeader(DnsHeader header) {
        super.setHeader(header);
        short flags = header.getFlags();
        short qr = (short) 0B1_0000_0_0_0_0_000_0000;
        short opcode = (short) (0B0_1111_0_0_0_0_000_0000 & flags);
        short rd = (short) (0B0_0000_0_0_1_0_000_0000 & flags);
        short rcode = (short) (opcode == 0 ? 0 : 0B0_0000_0_0_0_0_000_0100);
        flags = (short) (qr | opcode | rcode | rd);
        header.setFlags(flags);
    }

    @Override
    protected ByteBuffer byteBuffer() {
        ByteBuffer byteBuffer = super.byteBuffer();
        DnsUtil.writeAnswers(byteBuffer, getAnswers());
        return byteBuffer;
    }
}
