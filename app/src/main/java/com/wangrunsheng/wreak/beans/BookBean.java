package com.wangrunsheng.wreak.beans;

import java.util.List;

/**
 * Created by Russell on 2018/3/19.
 */

public class BookBean {

    /**
     * count : 20
     * start : 0
     * total : 94
     * books : [{"images":{"small":"https://img3.doubanio.com/spic/s1470003.jpg","large":"https://img3.doubanio.com/lpic/s1470003.jpg","medium":"https://img3.doubanio.com/mpic/s1470003.jpg"},"title":"深入理解计算机系统"},{"images":{"small":"https://img3.doubanio.com/spic/s4510534.jpg","large":"https://img3.doubanio.com/lpic/s4510534.jpg","medium":"https://img3.doubanio.com/mpic/s4510534.jpg"},"title":"深入理解计算机系统（原书第2版）"},{"images":{"small":"https://img1.doubanio.com/spic/s24505667.jpg","large":"https://img1.doubanio.com/lpic/s24505667.jpg","medium":"https://img1.doubanio.com/mpic/s24505667.jpg"},"title":"深入理解LINUX内核"},{"images":{"small":"https://img3.doubanio.com/spic/s27458236.jpg","large":"https://img3.doubanio.com/lpic/s27458236.jpg","medium":"https://img3.doubanio.com/mpic/s27458236.jpg"},"title":"深入理解Java虚拟机（第2版）"},{"images":{"small":"https://img3.doubanio.com/spic/s6564370.jpg","large":"https://img3.doubanio.com/lpic/s6564370.jpg","medium":"https://img3.doubanio.com/mpic/s6564370.jpg"},"title":"深入理解Java虚拟机"},{"images":{"small":"https://img3.doubanio.com/spic/s2008433.jpg","large":"https://img3.doubanio.com/lpic/s2008433.jpg","medium":"https://img3.doubanio.com/mpic/s2008433.jpg"},"title":"深入理解计算机系统"},{"images":{"small":"https://img3.doubanio.com/spic/s27314344.jpg","large":"https://img3.doubanio.com/lpic/s27314344.jpg","medium":"https://img3.doubanio.com/mpic/s27314344.jpg"},"title":"深入理解LINUX内核(第三版)"},{"images":{"small":"https://img1.doubanio.com/spic/s26036498.jpg","large":"https://img1.doubanio.com/lpic/s26036498.jpg","medium":"https://img1.doubanio.com/mpic/s26036498.jpg"},"title":"深入理解Nginx"},{"images":{"small":"https://img1.doubanio.com/spic/s4572259.jpg","large":"https://img1.doubanio.com/lpic/s4572259.jpg","medium":"https://img1.doubanio.com/mpic/s4572259.jpg"},"title":"深入理解计算机系统（英文版·第2版）"},{"images":{"small":"https://img3.doubanio.com/spic/s11171603.jpg","large":"https://img3.doubanio.com/lpic/s11171603.jpg","medium":"https://img3.doubanio.com/mpic/s11171603.jpg"},"title":"深入理解Android"},{"images":{"small":"https://img1.doubanio.com/spic/s2008439.jpg","large":"https://img1.doubanio.com/lpic/s2008439.jpg","medium":"https://img1.doubanio.com/mpic/s2008439.jpg"},"title":"深入理解LINUX网络内幕"},{"images":{"small":"https://img1.doubanio.com/spic/s29195878.jpg","large":"https://img1.doubanio.com/lpic/s29195878.jpg","medium":"https://img1.doubanio.com/mpic/s29195878.jpg"},"title":"深入理解计算机系统（原书第3版）"},{"images":{"small":"https://img3.doubanio.com/spic/s7656285.jpg","large":"https://img3.doubanio.com/lpic/s7656285.jpg","medium":"https://img3.doubanio.com/mpic/s7656285.jpg"},"title":"深入理解C#（第2版）"},{"images":{"small":"https://img3.doubanio.com/spic/s26689304.jpg","large":"https://img3.doubanio.com/lpic/s26689304.jpg","medium":"https://img3.doubanio.com/mpic/s26689304.jpg"},"title":"深入理解C++11"},{"images":{"small":"https://img3.doubanio.com/spic/s1781573.jpg","large":"https://img3.doubanio.com/lpic/s1781573.jpg","medium":"https://img3.doubanio.com/mpic/s1781573.jpg"},"title":"深入理解LINUX内核（第二版）"},{"images":{"small":"https://img3.doubanio.com/spic/s27175351.jpg","large":"https://img3.doubanio.com/lpic/s27175351.jpg","medium":"https://img3.doubanio.com/mpic/s27175351.jpg"},"title":"深入理解程序设计"},{"images":{"small":"https://img3.doubanio.com/spic/s5744920.jpg","large":"https://img3.doubanio.com/lpic/s5744920.jpg","medium":"https://img3.doubanio.com/mpic/s5744920.jpg"},"title":"深入理解Linux虚拟内存管理"},{"images":{"small":"https://img3.doubanio.com/spic/s11162474.jpg","large":"https://img3.doubanio.com/lpic/s11162474.jpg","medium":"https://img3.doubanio.com/mpic/s11162474.jpg"},"title":"深入理解Android"},{"images":{"small":"https://img3.doubanio.com/spic/s28513840.jpg","large":"https://img3.doubanio.com/lpic/s28513840.jpg","medium":"https://img3.doubanio.com/mpic/s28513840.jpg"},"title":"深入理解Nginx（第2版）"},{"images":{"small":"https://img3.doubanio.com/spic/s28289936.jpg","large":"https://img3.doubanio.com/lpic/s28289936.jpg","medium":"https://img3.doubanio.com/mpic/s28289936.jpg"},"title":"深入理解C#（第3版）"}]
     */

    private int count;
    private int start;
    private int total;
    private List<BooksBean> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean {
        /**
         * images : {"small":"https://img3.doubanio.com/spic/s1470003.jpg","large":"https://img3.doubanio.com/lpic/s1470003.jpg","medium":"https://img3.doubanio.com/mpic/s1470003.jpg"}
         * title : 深入理解计算机系统
         */

        private ImagesBean images;
        private String title;

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class ImagesBean {
            /**
             * small : https://img3.doubanio.com/spic/s1470003.jpg
             * large : https://img3.doubanio.com/lpic/s1470003.jpg
             * medium : https://img3.doubanio.com/mpic/s1470003.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
