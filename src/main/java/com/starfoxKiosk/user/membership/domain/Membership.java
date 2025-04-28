package com.starfoxKiosk.user.membership.domain;

public class Membership {
    private int membershipId;
    private String membershipName;
    private int defaultPrice;
    private int customId;       // 회원 ID (tbl_user.customId)



    public Membership(int membershipId, String membershipName, int defaultPrice, int customId) {
        this.membershipId = membershipId;
        this.membershipName = membershipName;
        this.defaultPrice = defaultPrice;
        this.customId = customId;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public int getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(int defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "membershipId=" + membershipId +
                ", membershipName='" + membershipName + '\'' +
                ", defaultPrice=" + defaultPrice +
                ", customId=" + customId +
                '}';
    }


}
