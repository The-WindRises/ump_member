package it.swiftelink.com.vcs_member.base;

import java.io.File;

public class Image implements Comparable{
    public int height;
    public int width;
    public File file;
    public int pos;

    public Image(File file, int width, int height ,int pos) {
        this.height = height;
        this.width = width;
        this.file = file;
        this.pos = pos ;

    }

    @Override
    public int compareTo(Object o) {
        Image s = (Image) o;
        if (this.pos > s.pos) {
            return 1;
        }
        else{
            return -1;
        }

    }
}