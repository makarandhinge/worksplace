package com.worksplace.MiniPro.Core.generic.L7.P1;

import java.util.function.Predicate;

public interface FromStage<T> {
    WhereStage<T> where(Predicate<T> condition);
}
