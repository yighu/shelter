package com.ffg.shelter.converter;

import com.ffg.shelter.exception.BusinessException;
import com.ffg.shelter.model.Client;
import com.ffg.shelter.view.ClientDetailView;
import com.ffg.shelter.view.ClientListView;
import com.ffg.shelter.view.ClientSearchView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;


public interface ClientTransformer {

    ClientSearchView transformFromEntityToSearchView(Client client);

    ClientListView transformFromEntityToListView(Client client);

    ClientDetailView transformFromEntityToDetailView(Client client);

    Client transformToEntityFromListView(ClientListView clientListView);

    Client transformToEntityFromDetailView(ClientDetailView clientDetailView) throws ParseException, BusinessException;

    Page<ClientListView> buildClientViewPage(PageRequest pageRequest) throws Exception;

    Page<ClientSearchView> buildCampsViewClientByNamePaginated(PageRequest pageRequest, String firstName) throws Exception;


}
