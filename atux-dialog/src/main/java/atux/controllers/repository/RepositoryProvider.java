package atux.controllers.repository;

/**
 * Created by IntelliJ IDEA.
 * User: razanero
 * Date: Aug 1, 2006
 * Time: 11:22:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class RepositoryProvider {

    public Repository getRepository(Class repositoryClass) {
        try {
            return (Repository) repositoryClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}