package kr.swmaestro.reportcover.listview;

/**
 * Created by Yoon-Jisoo on 2016-07-18.
 *
 * 대학 관련 자료를 위한 Value Object.
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
