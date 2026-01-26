package com.worksplace.MiniPro.Core.generic.L5;

import com.worksplace.MiniPro.Core.generic.L4.Identifiable;
import com.worksplace.MiniPro.Core.generic.L4.Repository;

public class RepositoryUtils {
    static void printAll(Repository<? extends Identifiable<?>, ?> repo){
      for(Identifiable<?> item : repo.findAll()){
          System.out.println(item.getId());
      }
    }
}
