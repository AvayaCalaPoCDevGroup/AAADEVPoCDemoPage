package service.AAADEVPoCDemoPage.Service;

import java.util.List;

import service.AAADEVPoCDemoPage.Dao.Exceptions.NonexistentEntityException;
import service.AAADEVPoCDemoPage.Entity.Collaterals;
import service.AAADEVPoCDemoPage.Entity.Components;
import service.AAADEVPoCDemoPage.Entity.Demo;
import service.AAADEVPoCDemoPage.Entity.Tag;

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
    Collaterals crearColateral(Collaterals collateral);
    Collaterals getByIdDemoAndId(Long id);
    boolean deleteCollateral(Long id);
    /*
    Components
     */
    List<Components> obtenerTodoslosComponentes();
    List<Components> obtenerComponentesPorIdDemo(String idDemo);
    Components createComponent(Components component);
    boolean deleteComponent(long id);
    
    /*
    Tags
     */
    List<Tag> obtenerTodoslosTags();
    List<Tag> obtenerTagsPorIdDemo(String idDemo);
    Tag createTag(Tag tag);
    boolean deleteTag(long id);

    /*
    Demo
     */
    List<Demo> obtenerTodoslosDemos();
    boolean CreateOrUpdateDemo(Demo demo) throws NonexistentEntityException, Exception;
    boolean DeleteDemo(long id);
}
