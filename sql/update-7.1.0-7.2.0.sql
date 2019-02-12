alter table Layout add parentPlid LONG;
alter table Layout add leftPlid LONG;
alter table Layout add rightPlid LONG;
alter table Layout add system_ BOOLEAN;

COMMIT_TRANSACTION;

update Layout set system_ = FALSE;