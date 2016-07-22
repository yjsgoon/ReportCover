package kr.swmaestro.reportcover;

/**
 * Created by JiSoo on 2016-07-18.
 *
 */
public class UnivInformation {
    private static final String TAG = "UnivInformation";

    private int univNumber;
    private String univName;
    private String fileName;
    private String imgName;
    private int refCount;

    public UnivInformation(int univNumber, String univName, String fileName, String imgName, int refCount) {
        this.univNumber = univNumber;
        this.univName = univName;
        this.fileName = fileName;
        this.imgName = imgName;
        this.refCount = refCount;
    }

    public int getUnivNumber() {
        return univNumber;
    }

    public String getUnivName() {
        return univName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getImgName() {
        return imgName;
    }

    public int getRefCount() {
        return refCount;
    }
}
