package com.ruoyi.system.domain;

public class B2bFavoriteProduct {

    private Long prod_id;
    private String img ;
    private String name;
    private String desc;

    @Override
    public String toString() {
        return "B2bFavoriteProduct{" +
                "prod_id=" + prod_id +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public Long getProd_id() {
        return prod_id;
    }

    public void setProd_id(Long prod_id) {
        this.prod_id = prod_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public B2bFavoriteProduct() {
    }

    public B2bFavoriteProduct(Long prod_id, String img, String name, String desc) {
        this.prod_id = prod_id;
        this.img = img;
        this.name = name;
        this.desc = desc;
    }
}
