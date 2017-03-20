package com.triplefighter.jurnal9;

/**
 * Created by Praktikan on 28/02/2017.
 */
public class Hewan {
    private String namaHewan;
    private String namaLatin;
    private int mImageResource;
    private int mAudioResource = NO_IMAGE_RSC;
    private final static int NO_IMAGE_RSC = -1;


    public Hewan(String namaHewan, String namaLatin, int audioResource) {
        this.namaHewan = namaHewan;
        this.namaLatin = namaLatin;
        mAudioResource = audioResource;
    }

    @Override
    public String toString() {
        return getNamaHewan() + " " + getNamaLatin();
    }

    public Hewan(String namaHewan, String namaLatin, int imageResource, int audioResource) {
        this.namaHewan = namaHewan;
        this.namaLatin = namaLatin;
        mImageResource = imageResource;
        mAudioResource = audioResource;
    }
    public int getAudioResource(){
        return mAudioResource;
    }

    public String getNamaHewan() {

        return namaHewan;
    }

    public String getNamaLatin() {

        return namaLatin;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public boolean cekGambar(){
        return mImageResource != NO_IMAGE_RSC;
    }

}
