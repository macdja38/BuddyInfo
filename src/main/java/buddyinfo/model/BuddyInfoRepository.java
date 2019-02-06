package buddyinfo.model;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.query.Param;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "buddyinfo", path = "people")

public interface BuddyInfoRepository extends PagingAndSortingRepository<BuddyInfo, Long> {
    List<BuddyInfo> findByName(@Param("name") String name);
}