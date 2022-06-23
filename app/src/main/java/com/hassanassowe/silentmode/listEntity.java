package com.hassanassowe.silentmode;

public class listEntity {
    private String mtextlayanan;
    private String mtextdokter;
    private String mtextdokter2;
    private Boolean mactive;

    public listEntity(String mtextlayanan, String mtextdokter, String mtextdokter2, Boolean mactive) {
        this.mtextlayanan = mtextlayanan;
        this.mtextdokter = mtextdokter;
        this.mtextdokter2 = mtextdokter2;
        this.mactive = mactive;
    }

    public String getMtextlayanan() {
        return mtextlayanan;
    }

    public void setMtextlayanan(String mtextlayanan) {
        this.mtextlayanan = mtextlayanan;
    }

    public String getMtextdokter() {
        return mtextdokter;
    }

    public void setMtextdokter(String mtextdokter) {
        this.mtextdokter = mtextdokter;
    }

    public String getMtextdokter2() {
        return mtextdokter2;
    }

    public void setMtextdokter2(String mtextdokter2) {
        this.mtextdokter2 = mtextdokter2;
    }

    public Boolean getMactive() {
        return mactive;
    }

    public void setMactive(Boolean mactive) {
        this.mactive = mactive;
    }
}
