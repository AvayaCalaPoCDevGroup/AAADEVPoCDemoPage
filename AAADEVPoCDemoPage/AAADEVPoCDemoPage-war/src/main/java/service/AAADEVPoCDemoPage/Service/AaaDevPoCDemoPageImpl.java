package service.AAADEVPoCDemoPage.Service;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import service.AAADEVPoCDemoPage.Dao.CollateralsJpaController;
import service.AAADEVPoCDemoPage.Dao.ComponentsJpaController;
import service.AAADEVPoCDemoPage.Dao.DemoJpaController;
import service.AAADEVPoCDemoPage.Entity.Collaterals;
import service.AAADEVPoCDemoPage.Entity.Components;
import service.AAADEVPoCDemoPage.Entity.Demo;
import service.AAADEVPoCDemoPage.Util.Constants;

/**
 *
 * @author umansilla
 */
public class AaaDevPoCDemoPageImpl implements IAaaDevPoCDemoPage {

    private final EntityManagerFactory emf;

    public AaaDevPoCDemoPageImpl() {
        this.emf = Persistence.createEntityManagerFactory(Constants.JPA_NAME);
    }

    /*
    Collaterals
     */
    @Override
    public List<Collaterals> obtenerTodoslosCollaterals() {
        return new CollateralsJpaController(emf).findCollateralsEntities();
    }

    @Override
    public List<Collaterals> obtenerTodosLosCollateralsPorIdDemo(String idDemo) {
       return new CollateralsJpaController(emf).obtenerCollateralsPorIdDemo(idDemo);
    }

    /*
    Components
     */
    @Override
    public List<Components> obtenerTodoslosComponentes() {
        return new ComponentsJpaController(emf).findComponentsEntities();
    }
    @Override
    public List<Components> obtenerComponentesPorIdDemo(String idDemo){
        return new ComponentsJpaController(emf).obtenerComponentsPorIdDemo(idDemo);
    }

    /*
    Demo
     */
    @Override
    public List<Demo> obtenerTodoslosDemos() {
        return new DemoJpaController(emf).findDemoEntities();
    }

}
