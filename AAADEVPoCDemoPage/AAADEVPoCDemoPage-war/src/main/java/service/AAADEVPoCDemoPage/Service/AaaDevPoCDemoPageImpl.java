package service.AAADEVPoCDemoPage.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import service.AAADEVPoCDemoPage.Dao.CollateralsJpaController;
import service.AAADEVPoCDemoPage.Dao.ComponentsJpaController;
import service.AAADEVPoCDemoPage.Dao.DemoJpaController;
import service.AAADEVPoCDemoPage.Dao.TagsJpaController;
import service.AAADEVPoCDemoPage.Dao.Exceptions.NonexistentEntityException;
import service.AAADEVPoCDemoPage.Entity.Collaterals;
import service.AAADEVPoCDemoPage.Entity.Components;
import service.AAADEVPoCDemoPage.Entity.Demo;
import service.AAADEVPoCDemoPage.Entity.Tag;
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

	@Override
	public List<Tag> obtenerTodoslosTags() {
		// TODO Auto-generated method stub
		return new TagsJpaController(emf).findTagEntities();
	}

	@Override
	public List<Tag> obtenerTagsPorIdDemo(String idDemo) {
		// TODO Auto-generated method stub
		return new TagsJpaController(emf).obtenerTagPorIdDemo(idDemo);
	}

	/**
     * Crea o actualiza una demo, retorna la demo creada o actualizada, null si ocurrio un problema
     * @param demo
     * @return
	 * @throws Exception 
	 * @throws NonexistentEntityException 
     */
	@Override
	public boolean CreateOrUpdateDemo(Demo demo) throws NonexistentEntityException, Exception {
		// TODO Auto-generated method stub
		boolean resp = false;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		demo.setUpdatedtime(formatter.format(date));
		if(demo.getId() == null) {
			//Create
			//demo.setId(null);
			new DemoJpaController(emf).create(demo);
			resp = true;
		} else {
			//Update
			new DemoJpaController(emf).edit(demo);
			resp = true;
		}
		return resp;
	}

	@Override
	public boolean DeleteDemo(long id) {
		// TODO Auto-generated method stub
		boolean resp = false;
		try {
			resp = new DemoJpaController(emf).destroy(id);
		} catch (NonexistentEntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}
	
	@Override
	public boolean deleteComponent(long id) {
		// TODO Auto-generated method stub
		boolean resp = false;
		try {
			resp = new ComponentsJpaController(emf).destroy(id);
		} catch (NonexistentEntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}
	
	@Override
	public boolean deleteTag(long id) {
		// TODO Auto-generated method stub
		boolean resp = false;
		try {
			resp = new TagsJpaController(emf).destroy(id);
		} catch (NonexistentEntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}

	@Override
	public Components createComponent(Components component) {
		// TODO Auto-generated method stub
		Components resp = null;

		component.setId(null);
		resp = new ComponentsJpaController(emf).create(component);
	
		return resp;
	}
	
	@Override
	public Tag createTag(Tag tag) {
		// TODO Auto-generated method stub
		Tag resp = null;

		tag.setId(null);
		resp = new TagsJpaController(emf).create(tag);
	
		return resp;
	}

}
