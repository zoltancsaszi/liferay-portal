create table SampleLAR_Booking (
	uuid_ VARCHAR(75) null,
	bookingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bookingNumber VARCHAR(75) null,
	lastPublishDate DATE null
);

create table SampleLAR_Room (
	uuid_ VARCHAR(75) null,
	roomId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bookingId LONG,
	roomNumber VARCHAR(75) null,
	lastPublishDate DATE null
);