alter table Layout add parentPlid LONG;
alter table Layout add leftPlid LONG;
alter table Layout add rightPlid LONG;

create table LayoutSetResource (
	mvccVersion LONG default 0 not null,
	layoutSetResourceId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	pageCount INTEGER,
	privateLayout BOOLEAN
);

create table LayoutSetVersion (
	mvccVersion LONG default 0 not null,
	layoutSetVersionId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	layoutSetResourceId LONG,
	logoId LONG,
	themeId VARCHAR(75) null,
	colorSchemeId VARCHAR(75) null,
	css VARCHAR(75) null,
	settings_ VARCHAR(75) null,
	layoutSetPrototypeUuid VARCHAR(75) null,
	layoutSetPrototypeLinkEnabled BOOLEAN
);

create index IX_49A7A301 on LayoutSetResource (companyId, privateLayout);
create unique index IX_A56FF93F on LayoutSetResource (groupId, privateLayout);

create index IX_C13612FF on LayoutSetVersion (layoutSetPrototypeUuid[$COLUMN_LENGTH:75$]);
create index IX_FB5A65AB on LayoutSetVersion (layoutSetResourceId);
