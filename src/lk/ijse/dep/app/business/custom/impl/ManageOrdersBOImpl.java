package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageOrdersBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.CustomerDAO;
import lk.ijse.dep.app.dao.custom.ItemDAO;
import lk.ijse.dep.app.dao.custom.OrderDAO;
import lk.ijse.dep.app.dto.ItemDTO;
import lk.ijse.dep.app.dto.OrderDTO;
import lk.ijse.dep.app.dto.OrderDTO2;
import lk.ijse.dep.app.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ManageOrdersBOImpl implements ManageOrdersBO {


    private OrderDAO orderDAO;
    private CustomerDAO customerDAO;
    private ItemDAO itemDAO;

    public ManageOrdersBOImpl() {
        customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        orderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    }

    @Override
    public List<OrderDTO2> getOrdersWithCustomerNamesAndTotals() throws Exception {
        return null;
    }

    @Override
    public List<OrderDTO> getOrders() throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            orderDAO.setEntityManager(em);
            em.getTransaction().begin();
            List<OrderDTO> orderDTOS = orderDAO.findAll().map(Converter::<OrderDTO>getDTOList).get();
            em.getTransaction().commit();
            return orderDTOS;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public String generateOrderId() throws Exception {
        return null;
    }

    @Override
    public void createOrder(OrderDTO dto) throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            orderDAO.setEntityManager(em);
            em.getTransaction().begin();
            orderDAO.save(Converter.getEntity(dto));
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public OrderDTO findOrder(String orderId) throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            itemDAO.setEntityManager(em);
            em.getTransaction().begin();
            OrderDTO orderDTO = orderDAO.find(orderId).map(Converter::<OrderDTO>getDTO).orElse(null);
            em.getTransaction().commit();
            return orderDTO;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }
}
