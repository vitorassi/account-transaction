create table public.account (
    id UUID not null,
    document_number varchar(255) not null,
    primary key (id)
);

create table public.transaction (
    id UUID not null,
    account_id UUID not null,
    amount numeric(18, 5) not null,
    event_date timestamp not null,
    operation_type int4 not null,
    primary key (id),
    CONSTRAINT fk_account FOREIGN KEY(account_id) REFERENCES account(id)
);

alter table public.account add constraint UK_account_document_number unique (document_number);

