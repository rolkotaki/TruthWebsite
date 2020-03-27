
package model;

import entities.Article;
import entities.Category;
import entities.HibernateUtil;
import entities.LeadingArticle;
import entities.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

public class DataBase {
    
    private static Session session = null;
    
    public static boolean saveArticle(Article art, String[] categories) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (int i = 0; i < categories.length; i++) {
                int id = Integer.parseInt(categories[i]);
                Category cat = (Category)session.load(Category.class, id);
                art.getCategories().add(cat);
            }
            session.save(art);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                return false;
            }
        } finally {
            if (session.isOpen())
                session.close();
        }
        return true;
    }
    
    public static boolean updateArticle(Article art, String[] categories) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            art.getCategories().clear();
            for (int i = 0; i < categories.length; i++) {
                int id = Integer.parseInt(categories[i]);
                Category cat = (Category)session.load(Category.class, id);
                art.getCategories().add(cat);
            }
            session.saveOrUpdate(art);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                return false;
            }        
        } finally {
            if (session.isOpen())
                session.close();
        }
        return true;
    }
    
    public static boolean deleteArticle(String id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int artId = Integer.parseInt(id);
            Article art = (Article) session.get(Article.class, (long)artId);
            session.delete(art);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                return false;
            }        
        } finally {
            if (session.isOpen())
                session.close();
        }
        return true;
    }
    
    public static Article getSingleArticle(String id) {
        Article art = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            long artId = Long.parseLong(id);
            art = (Article) session.get(Article.class, artId);
            art.getCategories().size();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }        
        } finally {
            if (session.isOpen())
                session.close();
        }
        return art;
    }
    
    public static int getTypeOfArticle(String id) {
        int type = -1;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            long artId = Long.parseLong(id);
            LeadingArticle la = (LeadingArticle) session.load(LeadingArticle.class, artId);
            if (la.isMainOrNot())
                type = 1;
            else if (!la.isMainOrNot())
                type = 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }        
        } finally {
            if (session.isOpen())
                session.close();
        }
        return type;
    }
    
    public static boolean updateLeadingToHome() {
        try {
        session.beginTransaction();
        Criteria critUpdate = session.createCriteria(LeadingArticle.class);
        critUpdate.add(Restrictions.eq("mainOrNot", Boolean.TRUE));
        List<LeadingArticle> list = critUpdate.list();
        for (LeadingArticle l: list) {
            l.setMainOrNot(false);
            session.update(l);
        }
        session.getTransaction().commit();
        } catch (HibernateException he) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            return false;
        }
        return true;
    }
    
    public static boolean deleteOldestHome() {
        try {
        session.beginTransaction();
        Criteria critOldest = session.createCriteria(LeadingArticle.class);
        critOldest.add(Restrictions.eq("mainOrNot", Boolean.FALSE));
        critOldest.setProjection(Projections.min("id"));
        LeadingArticle laDel = (LeadingArticle) critOldest.uniqueResult();
        session.delete(laDel);
        session.getTransaction().commit();
        } catch (HibernateException he) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            return false;
        }
        return true;
    }
    
    public static boolean saveLeadingHomeArticle(LeadingArticle la) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Integer numberHome = null;
            Integer numberLeading = null;
            Criteria critHome = session.createCriteria(LeadingArticle.class);
            critHome.add(Restrictions.eq("mainOrNot", Boolean.FALSE));
            critHome.setProjection(Projections.rowCount());
            numberHome = ((Number)critHome.uniqueResult()).intValue();
            Criteria critLeading = session.createCriteria(LeadingArticle.class);
            critLeading.add(Restrictions.eq("mainOrNot", Boolean.TRUE));
            critLeading.setProjection(Projections.rowCount());
            numberLeading = ((Number)critLeading.uniqueResult()).intValue();
            session.getTransaction().commit();

            if (la.isMainOrNot()) {
                if (numberLeading == 0) {
                    session.beginTransaction();
                    session.save(la);
                    session.getTransaction().commit();
                } else {
                    if (numberHome > 5) {
                        deleteOldestHome();
                        updateLeadingToHome();
                        session.beginTransaction();
                        session.save(la);
                        session.getTransaction().commit();
                    } else {
                        updateLeadingToHome();
                        session.beginTransaction();
                        session.save(la);
                        session.getTransaction().commit();
                    }
                }

            } else {
                if (numberHome > 5) {
                    deleteOldestHome();
                    session.beginTransaction();
                    session.save(la);
                    session.getTransaction().commit();
                } else {
                    session.beginTransaction();
                    session.save(la);
                    session.getTransaction().commit();
                }
            }
            
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return true;
    }
    
    public static boolean updateLeadingHomeArticle(long id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            LeadingArticle l = (LeadingArticle) session.get(LeadingArticle.class, id);
            if (l != null)
                session.delete(l);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return true;
    }
    
    public static List<Category> getCategories() {
        List<Category> list = null;
        try {
            System.out.println("STARTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
            session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            session.beginTransaction();
            list = session.createCriteria(Category.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return list;
    }
    
    public static boolean updateCategory(String id, String name) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int catId = Integer.parseInt(id);
            Category cat = (Category) session.get(Category.class, catId);
            if (!cat.getName().equals(name)) {
                cat.setName(name);
                session.update(cat);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                return false;
            }        
        } finally {
            if (session.isOpen())
                session.close();
        }
        return true;
    }
    
    public static boolean deleteCategory(String id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int catId = Integer.parseInt(id);
            Category cat = (Category) session.get(Category.class, catId);
            session.delete(cat);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                return false;
            }        
        } finally {
            if (session.isOpen())
                session.close();
        }
        return true;
    }
    
    public static boolean saveCategory(String name) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Category cat = new Category(name);
            session.save(cat);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                return false;
            }        
        } finally {
            if (session.isOpen())
                session.close();
        }
        return true;
    }
    
    public static List<Article> getAllArticles(int page) {
         List<Article> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list = session.createCriteria(Article.class).setFirstResult((page - 1) * Constants.MAX_ARTICLES_ON_PAGE).setMaxResults(Constants.MAX_ARTICLES_ON_PAGE).list();
            for (Article a : list)
                a.getCategories().size();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return list;
    }
    
    public static List<Article> getArticles(int page, String restriction) {
         List<Article> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "SELECT * FROM ARTICLES WHERE ";
            sql += restriction;
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Article.class);
            list = query.setFirstResult((page - 1) * Constants.MAX_ARTICLES_ON_PAGE).setMaxResults(Constants.MAX_ARTICLES_ON_PAGE).list();
            for (Article a : list)
                a.getCategories().size();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return list;
    }
    
    public static int getAllArticlesNumber(){
        int result = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = ((Number)session.createCriteria(Article.class).setProjection(Projections.rowCount()).uniqueResult()).intValue();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return result;
    }
    
    public static int getArticlesNumber(String restriction){
        int result = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "SELECT COUNT(*) FROM ARTICLES WHERE ";
            sql += restriction;
            SQLQuery query = session.createSQLQuery(sql);
            result = ((Number)query.uniqueResult()).intValue();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return result;
    }
    
    public static Article getLeadingArticle() {
        Article leading = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            LeadingArticle la = (LeadingArticle) session.createCriteria(LeadingArticle.class).add(Restrictions.eq("mainOrNot", Boolean.TRUE)).uniqueResult();
            leading = la.getArticle();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return leading;
    }
    
    public static List<Article> getHomeArticles() {
        List<Article> homeList = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<LeadingArticle> artList = session.createCriteria(LeadingArticle.class).add(Restrictions.eq("mainOrNot", Boolean.FALSE)).addOrder(Order.desc("id")).list();
            for (LeadingArticle l: artList)
                homeList.add(l.getArticle());
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return homeList;
    }
    
    public static List<Map<String,Object>> runSQLQuery(String statement) {
        List<Map<String,Object>> resultSet = new ArrayList<>();
        Map<String,Object> m = new HashMap<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery(statement);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            query.setFirstResult(0).setMaxResults(Constants.MAX_SQL_RESULT_ON_PAGE);
            resultSet = query.list();
            session.getTransaction().commit();
        } catch (SQLGrammarException me) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            m.put("error_number", -1);
            resultSet.add(m);
        } catch (HibernateException he) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            m.put("error_number", -2);
            resultSet.add(m);
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            m.put("error_number", -99);
            resultSet.add(m);
        } finally {
            if (session.isOpen())
                session.close();
        }
        return resultSet;
    }
    
    public static int runSQLUpdate(String statement) {
        int result = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery(statement);
            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (SQLGrammarException me) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            result = -1;
        } catch (HibernateException he) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            result = -2;
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            result = -99;
        } finally {
            if (session.isOpen())
                session.close();
        }
        return result;
    }
    
    public static boolean checkUser(String userName) {
        boolean exists = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int count = 0;
            Criteria critHome = session.createCriteria(User.class).add(Restrictions.eq("userName", userName)).setProjection(Projections.rowCount());
            count = ((Number)critHome.uniqueResult()).intValue();
            if (count > 0)
                exists = true;
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session.isOpen())
                session.close();
        }
        return exists;
    }
    
    public static boolean saveUser(User user) {
        boolean saved = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            saved = true;
        } catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            saved = false;
        } finally {
            if (session.isOpen())
                session.close();
        }
        return saved;
    }
    
    public static int checkUserLogin(String userName, String pwd) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            User user = (User) session.createCriteria(User.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", pwd)).uniqueResult();
            if (user == null) {
                throw new NoSuchUserException();
            }
            session.getTransaction().commit();
        } catch (NoSuchUserException nue) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                return 0;
            }  
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                return -1;
            }        
        } finally {  
            if (session.isOpen())
                session.close();
        }
        return 1;
    }
    
    public static int changeUserPassword(String userName, String pwd) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            User user = (User) session.createCriteria(User.class).add(Restrictions.eq("userName", userName)).uniqueResult();
            if (user == null) {
                throw new NoSuchUserException();
            }
            user.setPassword(pwd);
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                return -1;
            }        
        } finally {  
            if (session.isOpen())
                session.close();
        }
        return 1;
    }
    
    
    
    /////////////////////////////////
    public static void setAdminInitialData() {
        try {
            System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            User user = new User(Constants.ADMIN_USERNAME, "admin@email.hu", true, 1991, PasswordEncryptor.encryptPassword("alma"));
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }        
        } finally {  
            if (session.isOpen())
                session.close();
        }
    }
    
}