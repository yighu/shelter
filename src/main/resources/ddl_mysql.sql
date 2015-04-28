
    alter table CampStats_Camp 
        drop 
        foreign key FK6871A3C237CF1A0F;

    alter table CampStats_Camp 
        drop 
        foreign key FK6871A3C2B357A8D9;

    alter table Camp_CampStats 
        drop 
        foreign key FK2CCB66049C50CF8;

    alter table Camp_CampStats 
        drop 
        foreign key FK2CCB660A404CF3C;

    alter table Camp_Client 
        drop 
        foreign key FK353D67491D50A905;

    alter table Camp_Client 
        drop 
        foreign key FK353D67497624DF7E;

    alter table Camp_CsbUser 
        drop 
        foreign key FK7DF7951F72FC628A;

    alter table Camp_CsbUser 
        drop 
        foreign key FK7DF7951FA404CF3C;

    alter table Client_Camp 
        drop 
        foreign key FK58F90275A404CF3C;

    alter table Client_Camp 
        drop 
        foreign key FK58F9027547CE127C;

    alter table Client_Needs 
        drop 
        foreign key FKC6C401291930BBB8;

    alter table Client_Needs 
        drop 
        foreign key FKC6C4012947CE127C;

    alter table Feature_PrivilegeType 
        drop 
        foreign key FK42E19FC26D279718;

    alter table Feature_PrivilegeType 
        drop 
        foreign key FK42E19FC2F994E198;

    alter table Feature_Role 
        drop 
        foreign key FK51CEF05FD55A0F1C;

    alter table Feature_Role 
        drop 
        foreign key FK51CEF05FF994E198;

    alter table Needs_Client 
        drop 
        foreign key FK261EEBED1D50A905;

    alter table Needs_Client 
        drop 
        foreign key FK261EEBED34FE031A;

    alter table PrivilegeType_Feature 
        drop 
        foreign key FKB3A188024C868236;

    alter table PrivilegeType_Feature 
        drop 
        foreign key FKB3A18802D728576C;

    alter table Role_Feature 
        drop 
        foreign key FK4976B82D4C868236;

    alter table Role_Feature 
        drop 
        foreign key FK4976B82DEC2A6053;

    drop table if exists Camp;

    drop table if exists CampStats;

    drop table if exists CampStats_Camp;

    drop table if exists Camp_CampStats;

    drop table if exists Camp_Client;

    drop table if exists Camp_CsbUser;

    drop table if exists CaseNotes;

    drop table if exists Child;

    drop table if exists Client;

    drop table if exists Client_Camp;

    drop table if exists Client_Needs;

    drop table if exists CsbUser;

    drop table if exists Event;

    drop table if exists Feature;

    drop table if exists Feature_PrivilegeType;

    drop table if exists Feature_Role;

    drop table if exists Image;

    drop table if exists Needs;

    drop table if exists Needs_Client;

    drop table if exists NewsFeed;

    drop table if exists PrivilegeType;

    drop table if exists PrivilegeType_Feature;

    drop table if exists Role;

    drop table if exists Role_Feature;

    create table Camp (
        camp_id bigint not null auto_increment,
        alert integer,
        badge varchar(255),
        description varchar(255),
        latitude varchar(255),
        longitude varchar(255),
        name varchar(255),
        scrubDate datetime,
        setUpDate datetime,
        status integer,
        streetAddress varchar(255),
        type varchar(255),
        primary key (camp_id)
    );

    create table CampStats (
        campStats_id bigint not null auto_increment,
        campStat varchar(255),
        count integer not null,
        primary key (campStats_id)
    );

    create table CampStats_Camp (
        CampStats_campStats_id bigint not null,
        camps_camp_id bigint not null,
        primary key (CampStats_campStats_id, camps_camp_id)
    );

    create table Camp_CampStats (
        camp_id bigint not null,
        campStats_id bigint not null,
        primary key (camp_id, campStats_id)
    );

    create table Camp_Client (
        Camp_camp_id bigint not null,
        clients_client_id bigint not null,
        primary key (Camp_camp_id, clients_client_id)
    );

    create table Camp_CsbUser (
        camp_id bigint not null,
        user_id bigint not null,
        primary key (camp_id, user_id)
    );

    create table CaseNotes (
        id bigint not null auto_increment,
        caseMng varchar(255),
        clientId bigint,
        createdDate datetime,
        housingStage integer,
        note varchar(255),
        primary key (id)
    );

    create table Child (
        id bigint not null auto_increment,
        dob varchar(255),
        firstName varchar(255),
        gender varchar(255),
        lastName varchar(255),
        primary key (id)
    );

    create table Client (
        client_id bigint not null auto_increment,
        alias varchar(255),
        attitude varchar(255),
        attitudeTowardHousing varchar(255),
        barriers varchar(255),
        caseMgr varchar(255),
        caseNotes varchar(255),
        clientPriority varchar(255),
        contactROI varchar(255),
        dateOfBirth datetime,
        documents varchar(255),
        emergencyContactName varchar(255),
        emergencyContactPhone varchar(255),
        firstName varchar(255),
        gender varchar(255),
        healthStatus varchar(255),
        healthStatusDescription varchar(255),
        housingStage varchar(255),
        imageROI varchar(255),
        lastContactDate datetime,
        lastName varchar(255),
        mentalStatus varchar(255),
        mentalStatusDescription varchar(255),
        numOfChildren varchar(255),
        numPets varchar(255),
        pregnant varchar(255),
        primaryPhone varchar(255),
        secondaryPhone varchar(255),
        status varchar(255),
        substanceAbuseStatus varchar(255),
        substanceDescription varchar(255),
        violentDescription varchar(255),
        violentStatus varchar(255),
        primary key (client_id)
    );

    create table Client_Camp (
        client_id bigint not null,
        camp_id bigint not null,
        primary key (client_id, camp_id)
    );

    create table Client_Needs (
        client_id bigint not null,
        needs_id bigint not null,
        primary key (client_id, needs_id)
    );

    create table CsbUser (
        user_id bigint not null auto_increment,
        email varchar(255),
        expiryDate datetime,
        firstName varchar(255),
        lastName varchar(255),
        role varchar(255),
        primary key (user_id)
    );

    create table Event (
        id bigint not null auto_increment,
        campId bigint,
        eventDate datetime,
        eventExpiryDate datetime,
        eventOwner bigint,
        eventType varchar(255),
        comment varchar(255),
        primary key (id)
    );

    create table Feature (
        feature_id bigint not null auto_increment,
        featureName varchar(255),
        workFlowName varchar(255),
        primary key (feature_id)
    );

    create table Feature_PrivilegeType (
        feature_id bigint not null,
        privilegeType_id bigint not null
    );

    create table Feature_Role (
        feature_id bigint not null,
        role_id bigint not null
    );

    create table Image (
        id bigint not null auto_increment,
        campId bigint,
        clientId bigint,
        imageContent longblob,
        primary key (id)
    );

    create table Needs (
        needs_id bigint not null auto_increment,
        need integer,
        primary key (needs_id)
    );

    create table Needs_Client (
        Needs_needs_id bigint not null,
        clients_client_id bigint not null,
        primary key (Needs_needs_id, clients_client_id)
    );

    create table NewsFeed (
        id bigint not null auto_increment,
        campId bigint,
        comments varchar(255),
        createdBy varchar(255),
        createdDate datetime,
        eventId bigint,
        expirationDate datetime,
        messageType varchar(255),
        primary key (id)
    );

    create table PrivilegeType (
        privilegeType_id bigint not null auto_increment,
        privilegeType varchar(255),
        primary key (privilegeType_id)
    );

    create table PrivilegeType_Feature (
        PrivilegeType_privilegeType_id bigint not null,
        listOfFeature_feature_id bigint not null
    );

    create table Role (
        role_id bigint not null auto_increment,
        roleType varchar(255),
        primary key (role_id)
    );

    create table Role_Feature (
        Role_role_id bigint not null,
        listOfFeature_feature_id bigint not null
    );

    alter table CampStats_Camp 
        add index FK6871A3C237CF1A0F (camps_camp_id), 
        add constraint FK6871A3C237CF1A0F 
        foreign key (camps_camp_id) 
        references Camp (camp_id);

    alter table CampStats_Camp 
        add index FK6871A3C2B357A8D9 (CampStats_campStats_id), 
        add constraint FK6871A3C2B357A8D9 
        foreign key (CampStats_campStats_id) 
        references CampStats (campStats_id);

    alter table Camp_CampStats 
        add index FK2CCB66049C50CF8 (campStats_id), 
        add constraint FK2CCB66049C50CF8 
        foreign key (campStats_id) 
        references CampStats (campStats_id);

    alter table Camp_CampStats 
        add index FK2CCB660A404CF3C (camp_id), 
        add constraint FK2CCB660A404CF3C 
        foreign key (camp_id) 
        references Camp (camp_id);

    alter table Camp_Client 
        add index FK353D67491D50A905 (clients_client_id), 
        add constraint FK353D67491D50A905 
        foreign key (clients_client_id) 
        references Client (client_id);

    alter table Camp_Client 
        add index FK353D67497624DF7E (Camp_camp_id), 
        add constraint FK353D67497624DF7E 
        foreign key (Camp_camp_id) 
        references Camp (camp_id);

    alter table Camp_CsbUser 
        add index FK7DF7951F72FC628A (user_id), 
        add constraint FK7DF7951F72FC628A 
        foreign key (user_id) 
        references CsbUser (user_id);

    alter table Camp_CsbUser 
        add index FK7DF7951FA404CF3C (camp_id), 
        add constraint FK7DF7951FA404CF3C 
        foreign key (camp_id) 
        references Camp (camp_id);

    alter table Client_Camp 
        add index FK58F90275A404CF3C (camp_id), 
        add constraint FK58F90275A404CF3C 
        foreign key (camp_id) 
        references Camp (camp_id);

    alter table Client_Camp 
        add index FK58F9027547CE127C (client_id), 
        add constraint FK58F9027547CE127C 
        foreign key (client_id) 
        references Client (client_id);

    alter table Client_Needs 
        add index FKC6C401291930BBB8 (needs_id), 
        add constraint FKC6C401291930BBB8 
        foreign key (needs_id) 
        references Needs (needs_id);

    alter table Client_Needs 
        add index FKC6C4012947CE127C (client_id), 
        add constraint FKC6C4012947CE127C 
        foreign key (client_id) 
        references Client (client_id);

    create index eventCampIndex on Event (campId);

    create index eventDateIndex on Event (eventDate);

    create index eventOwnerIndex on Event (eventOwner);

    alter table Feature_PrivilegeType 
        add index FK42E19FC26D279718 (privilegeType_id), 
        add constraint FK42E19FC26D279718 
        foreign key (privilegeType_id) 
        references PrivilegeType (privilegeType_id);

    alter table Feature_PrivilegeType 
        add index FK42E19FC2F994E198 (feature_id), 
        add constraint FK42E19FC2F994E198 
        foreign key (feature_id) 
        references Feature (feature_id);

    alter table Feature_Role 
        add index FK51CEF05FD55A0F1C (role_id), 
        add constraint FK51CEF05FD55A0F1C 
        foreign key (role_id) 
        references Role (role_id);

    alter table Feature_Role 
        add index FK51CEF05FF994E198 (feature_id), 
        add constraint FK51CEF05FF994E198 
        foreign key (feature_id) 
        references Feature (feature_id);

    alter table Needs_Client 
        add index FK261EEBED1D50A905 (clients_client_id), 
        add constraint FK261EEBED1D50A905 
        foreign key (clients_client_id) 
        references Client (client_id);

    alter table Needs_Client 
        add index FK261EEBED34FE031A (Needs_needs_id), 
        add constraint FK261EEBED34FE031A 
        foreign key (Needs_needs_id) 
        references Needs (needs_id);

    alter table PrivilegeType_Feature 
        add index FKB3A188024C868236 (listOfFeature_feature_id), 
        add constraint FKB3A188024C868236 
        foreign key (listOfFeature_feature_id) 
        references Feature (feature_id);

    alter table PrivilegeType_Feature 
        add index FKB3A18802D728576C (PrivilegeType_privilegeType_id), 
        add constraint FKB3A18802D728576C 
        foreign key (PrivilegeType_privilegeType_id) 
        references PrivilegeType (privilegeType_id);

    alter table Role_Feature 
        add index FK4976B82D4C868236 (listOfFeature_feature_id), 
        add constraint FK4976B82D4C868236 
        foreign key (listOfFeature_feature_id) 
        references Feature (feature_id);

    alter table Role_Feature 
        add index FK4976B82DEC2A6053 (Role_role_id), 
        add constraint FK4976B82DEC2A6053 
        foreign key (Role_role_id) 
        references Role (role_id);
