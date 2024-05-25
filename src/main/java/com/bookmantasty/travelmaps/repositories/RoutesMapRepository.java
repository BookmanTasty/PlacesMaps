package com.bookmantasty.travelmaps.repositories;

import com.bookmantasty.travelmaps.models.RoutesMap;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;
@ApplicationScoped
public class RoutesMapRepository implements PanacheRepositoryBase<RoutesMap, UUID>{
}
