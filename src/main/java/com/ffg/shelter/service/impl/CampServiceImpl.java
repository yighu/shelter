package com.ffg.shelter.service.impl;

import com.ffg.shelter.model.*;
import com.ffg.shelter.repository.CampRepository;
import com.ffg.shelter.repository.CampStatsRepository;
import com.ffg.shelter.service.CampService;
import com.ffg.shelter.view.ClientDetailView;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;


@Transactional
@Named("campService")
public class CampServiceImpl extends CrudServiceImpl<Camp, Long, CampRepository> implements CampService {

    private CampStatsRepository campStatsRepository;

    @Inject
    @Named("campStatsRepository")
    public void setCampStatsRepository(CampStatsRepository campStatsRepository) {
        this.campStatsRepository = campStatsRepository;
    }

    @Override
    @Inject
    public void setRepository(CampRepository repository) {
        super.setRepository(repository);
    }


    public List<Camp> findByCampName(String campName) {
        List<Camp> camps = this.repository.findByCampName(campName);
        return camps;
    }

    public Camp findById(Long campId) {
        Camp camp = this.repository.findById(campId);
        return camp;
    }

    public Camp findIdByCampName(String name) {
        Camp camp = this.repository.findIdByCampName(name);
        return camp;
    }

    public void updateCampOccupantCount(ClientDetailView clientDetailView, Client client) {
        if (clientDetailView.getPrimaryCamp() != null) {
            Camp camp = findIdByCampName(clientDetailView.getPrimaryCamp());

            Set<CampStats> campStatsList = camp.getCampStats();
            if (campStatsList.size() != 0) {


                for (CampStats campStats : campStatsList) {
                    if (client.getGender() == Gender.Female) {
                        if (campStats.getCampStat() == CampStat.Female) {
                            campStats.setCount(campStats.getCount() + 1);
                            campStatsRepository.save(campStats);
                        }
                    }
                    if (client.getGender() == Gender.Female) {
                        if (campStats.getCampStat() == CampStat.Male) {
                            campStats.setCount(campStats.getCount() + 1);
                            campStatsRepository.save(campStats);
                        }
                    }
                    if (clientDetailView.getNumOfChildren() != null) {
                        if (campStats.getCampStat() == CampStat.Children) {
                            campStats.setCount(campStats.getCount() + new Integer(clientDetailView.getNumOfChildren()));
                            campStatsRepository.save(campStats);
                        }
                    }
                    if (clientDetailView.getNumPets() != null) {
                        if (campStats.getCampStat() == CampStat.Animal) {
                            campStats.setCount(campStats.getCount() + new Integer(clientDetailView.getNumPets()));
                            campStatsRepository.save(campStats);
                        }
                    }

                }
            } else {

                if (client.getGender() == Gender.Female) {
                    CampStats campStats = new CampStats();
                    campStats.setCampStat(CampStat.Female);
                    campStats.setCount(1);
                    campStatsRepository.save(campStats);
                    campStatsList.add(campStats);
                }

                if (client.getGender() == Gender.Female) {
                    CampStats campStats = new CampStats();
                    campStats.setCampStat(CampStat.Male);
                    campStats.setCount(1);
                    campStatsRepository.save(campStats);
                    campStatsList.add(campStats);
                }

                if (clientDetailView.getNumOfChildren() != null) {
                    CampStats campStats = new CampStats();
                    campStats.setCampStat(CampStat.Children);
                    campStats.setCount(new Integer(clientDetailView.getNumOfChildren()));
                    campStatsRepository.save(campStats);
                    campStatsList.add(campStats);

                }
                if (clientDetailView.getNumPets() != null) {
                    CampStats campStats = new CampStats();
                    campStats.setCampStat(CampStat.Animal);
                    campStats.setCount(new Integer(clientDetailView.getNumPets()));
                    campStatsRepository.save(campStats);
                    campStatsList.add(campStats);

                }

            }

            update(camp);
        }


    }

}
