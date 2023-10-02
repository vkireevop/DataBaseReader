toc.dat                                                                                             0000600 0004000 0002000 00000015715 14506435172 0014457 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP           #            	    {         	   BuyerBase    15.3    15.3                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                    0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                    0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                    1262    17359 	   BuyerBase    DATABASE        CREATE DATABASE "BuyerBase" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "BuyerBase";
                postgres    false         �            1259    17361    buyers    TABLE     �   CREATE TABLE public.buyers (
    id integer NOT NULL,
    first_name character varying(255),
    last_name character varying(255)
);
    DROP TABLE public.buyers;
       public         heap    postgres    false         �            1259    17360    buyers_id_seq    SEQUENCE     �   CREATE SEQUENCE public.buyers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.buyers_id_seq;
       public          postgres    false    215                    0    0    buyers_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.buyers_id_seq OWNED BY public.buyers.id;
          public          postgres    false    214         �            1259    17370    products    TABLE     t   CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(255),
    price numeric(10,2)
);
    DROP TABLE public.products;
       public         heap    postgres    false         �            1259    17369    products_id_seq    SEQUENCE     �   CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public          postgres    false    217                    0    0    products_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;
          public          postgres    false    216         �            1259    17377 	   purchases    TABLE     �   CREATE TABLE public.purchases (
    id integer NOT NULL,
    buyer_id integer,
    product_id integer,
    purchase_date date
);
    DROP TABLE public.purchases;
       public         heap    postgres    false         �            1259    17376    purchases_id_seq    SEQUENCE     �   CREATE SEQUENCE public.purchases_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.purchases_id_seq;
       public          postgres    false    219                    0    0    purchases_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.purchases_id_seq OWNED BY public.purchases.id;
          public          postgres    false    218         o           2604    17364 	   buyers id    DEFAULT     f   ALTER TABLE ONLY public.buyers ALTER COLUMN id SET DEFAULT nextval('public.buyers_id_seq'::regclass);
 8   ALTER TABLE public.buyers ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215         p           2604    17373    products id    DEFAULT     j   ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 :   ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217         q           2604    17380    purchases id    DEFAULT     l   ALTER TABLE ONLY public.purchases ALTER COLUMN id SET DEFAULT nextval('public.purchases_id_seq'::regclass);
 ;   ALTER TABLE public.purchases ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219         	          0    17361    buyers 
   TABLE DATA           ;   COPY public.buyers (id, first_name, last_name) FROM stdin;
    public          postgres    false    215       3337.dat           0    17370    products 
   TABLE DATA           3   COPY public.products (id, name, price) FROM stdin;
    public          postgres    false    217       3339.dat           0    17377 	   purchases 
   TABLE DATA           L   COPY public.purchases (id, buyer_id, product_id, purchase_date) FROM stdin;
    public          postgres    false    219       3341.dat            0    0    buyers_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.buyers_id_seq', 3, true);
          public          postgres    false    214                    0    0    products_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.products_id_seq', 3, true);
          public          postgres    false    216                    0    0    purchases_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.purchases_id_seq', 6, true);
          public          postgres    false    218         s           2606    17368    buyers buyers_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.buyers
    ADD CONSTRAINT buyers_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.buyers DROP CONSTRAINT buyers_pkey;
       public            postgres    false    215         u           2606    17375    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public            postgres    false    217         w           2606    17382    purchases purchases_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.purchases DROP CONSTRAINT purchases_pkey;
       public            postgres    false    219         x           2606    17383 !   purchases purchases_buyer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_buyer_id_fkey FOREIGN KEY (buyer_id) REFERENCES public.buyers(id);
 K   ALTER TABLE ONLY public.purchases DROP CONSTRAINT purchases_buyer_id_fkey;
       public          postgres    false    215    3187    219         y           2606    17388 #   purchases purchases_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);
 M   ALTER TABLE ONLY public.purchases DROP CONSTRAINT purchases_product_id_fkey;
       public          postgres    false    217    219    3189                                                           3337.dat                                                                                            0000600 0004000 0002000 00000000047 14506435172 0014261 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	John	Doe
2	Jane	Doe
3	Bob	Smith
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         3339.dat                                                                                            0000600 0004000 0002000 00000000070 14506435172 0014257 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Phone	500.00
2	Laptop	1000.00
3	Headphones	50.00
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                        3341.dat                                                                                            0000600 0004000 0002000 00000000153 14506435172 0014252 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	1	1	2023-09-28
2	2	2	2023-09-29
3	3	3	2023-09-30
4	1	2	2023-09-30
5	2	3	2023-09-28
6	3	1	2023-09-29
\.


                                                                                                                                                                                                                                                                                                                                                                                                                     restore.sql                                                                                         0000600 0004000 0002000 00000013651 14506435172 0015401 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "BuyerBase";
--
-- Name: BuyerBase; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "BuyerBase" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE "BuyerBase" OWNER TO postgres;

\connect "BuyerBase"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: buyers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.buyers (
    id integer NOT NULL,
    first_name character varying(255),
    last_name character varying(255)
);


ALTER TABLE public.buyers OWNER TO postgres;

--
-- Name: buyers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.buyers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.buyers_id_seq OWNER TO postgres;

--
-- Name: buyers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.buyers_id_seq OWNED BY public.buyers.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(255),
    price numeric(10,2)
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: purchases; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchases (
    id integer NOT NULL,
    buyer_id integer,
    product_id integer,
    purchase_date date
);


ALTER TABLE public.purchases OWNER TO postgres;

--
-- Name: purchases_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.purchases_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.purchases_id_seq OWNER TO postgres;

--
-- Name: purchases_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.purchases_id_seq OWNED BY public.purchases.id;


--
-- Name: buyers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buyers ALTER COLUMN id SET DEFAULT nextval('public.buyers_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: purchases id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases ALTER COLUMN id SET DEFAULT nextval('public.purchases_id_seq'::regclass);


--
-- Data for Name: buyers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.buyers (id, first_name, last_name) FROM stdin;
\.
COPY public.buyers (id, first_name, last_name) FROM '$$PATH$$/3337.dat';

--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, name, price) FROM stdin;
\.
COPY public.products (id, name, price) FROM '$$PATH$$/3339.dat';

--
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.purchases (id, buyer_id, product_id, purchase_date) FROM stdin;
\.
COPY public.purchases (id, buyer_id, product_id, purchase_date) FROM '$$PATH$$/3341.dat';

--
-- Name: buyers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.buyers_id_seq', 3, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 3, true);


--
-- Name: purchases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchases_id_seq', 6, true);


--
-- Name: buyers buyers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buyers
    ADD CONSTRAINT buyers_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id);


--
-- Name: purchases purchases_buyer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_buyer_id_fkey FOREIGN KEY (buyer_id) REFERENCES public.buyers(id);


--
-- Name: purchases purchases_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       