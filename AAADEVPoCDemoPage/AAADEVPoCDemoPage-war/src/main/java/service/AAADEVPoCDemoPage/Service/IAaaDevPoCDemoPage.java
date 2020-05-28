package service.AAADEVPoCDemoPage.Service;

import java.util.List;

import service.AAADEVPoCDemoPage.Entity.Collaterals;
import service.AAADEVPoCDemoPage.Entity.Components;
import service.AAADEVPoCDemoPage.Entity.Demo;

/**
 *
 * @author umansilla
 */
public interface IAaaDevPoCDemoPage {

    /*
    Collaterals
     */
    List<Collaterals> obtenerTodoslosCollaterals();
    List<Collaterals> obtenerTodosLosCollateralsPorIdDemo(String idDemo);
    /*
    Components
     */
    List<Components> obtenerTodoslosComponentes();
    List<Components> obtenerComponentesPorIdDemo(String idDemo);

    /*
    Demo
     */
    List<Demo> obtenerTodoslosDemos();
}
