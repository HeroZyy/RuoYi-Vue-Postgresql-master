package com.ruoyi.system.domain;

import java.util.List;

public class B2bSceneWithClass {
    private List<B2bClassWithProdPro> classWithProdPros;

    private B2bScene scene;

    public B2bSceneWithClass() {
    }

    public List<B2bClassWithProdPro> getClassWithProdPros() {
        return classWithProdPros;
    }

    public void setClassWithProdPros(List<B2bClassWithProdPro> classWithProdPros) {
        this.classWithProdPros = classWithProdPros;
    }

    public B2bScene getScene() {
        return scene;
    }

    public void setScene(B2bScene scene) {
        this.scene = scene;
    }

    public B2bSceneWithClass(List<B2bClassWithProdPro> classWithProdPros, B2bScene scene) {
        this.classWithProdPros = classWithProdPros;
        this.scene = scene;
    }
}
