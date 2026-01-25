package generic.L5;

import generic.L4.Identifiable;
import generic.L4.Repository;

public class RepositoryUtils {
    static void printAll(Repository<? extends Identifiable<?>, ?> repo){
      for(Identifiable<?> item : repo.findAll()){
          System.out.println(item.getId());
      }
    }
}
