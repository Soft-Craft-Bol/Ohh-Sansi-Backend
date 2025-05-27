package com.softcraft.ohhsansibackend.area.domain.models;

public class PdfOlimpiada {
    private int idPdfConvocatoria;
    private String pdfBase64;
    public PdfOlimpiada() {
    }

    public PdfOlimpiada(int idPdfConvocatoria, String pdfBase64) {
        this.idPdfConvocatoria = idPdfConvocatoria;
        this.pdfBase64 = pdfBase64;
    }

    public int getIdPdfConvocatoria() {
        return idPdfConvocatoria;
    }

    public void setIdPdfConvocatoria(int idPdfConvocatoria) {
        this.idPdfConvocatoria = idPdfConvocatoria;
    }

    public String getPdfBase64() {
        return pdfBase64;
    }

    public void setPdfBase64(String pdfBase64) {
        this.pdfBase64 = pdfBase64;
    }
}
