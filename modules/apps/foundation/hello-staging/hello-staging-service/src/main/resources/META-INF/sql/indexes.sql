create index IX_F1A7DF8D on SampleLAR_Booking (groupId, bookingId);
create index IX_582F8327 on SampleLAR_Booking (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2E3CA769 on SampleLAR_Booking (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3C3C1DF on SampleLAR_Room (groupId, bookingId, roomNumber[$COLUMN_LENGTH:75$]);
create index IX_28D2CF5 on SampleLAR_Room (groupId, roomNumber[$COLUMN_LENGTH:75$]);
create index IX_8F7CDD01 on SampleLAR_Room (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_58EAAFC3 on SampleLAR_Room (uuid_[$COLUMN_LENGTH:75$], groupId);