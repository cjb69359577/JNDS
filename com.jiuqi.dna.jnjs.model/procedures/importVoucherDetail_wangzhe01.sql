  --插入其他子表相关数据
  create or replace procedure importVoucherDetail_wangzhe01
(
  p_mrecid    in varchar2,
  p_year      in number,
  p_itemorder in number,
  p_debit     in number,
  p_credit    in number,
  p_km_code   in varchar2,
  p_xm_code   in varchar2,
  p_gn_code   in varchar2,
  p_jj_code   in varchar2,
  p_message   out NVARCHAR2
) is
  m_recordnum NUMBER := 0;
  m_kmid      raw(16);
  m_xmid      raw(16);
  m_gnid      raw(16);
  m_jjid      raw(16);
  m_empty     raw(16) := hextoraw('00000000000000000000000000000000');
  m_asscombid raw(16);
begin
  --获取科目recid
  select count(1)
    into m_recordnum
    from md_accountsubject jc
   where jc.acctyear = p_year
     and jc.stdcode = p_km_code;

  if (m_recordnum > 0) then
    select jc.recid
      into m_kmid
      from md_accountsubject jc
     where jc.acctyear = p_year
       and jc.stdcode = p_km_code
       and not exists (select 1
              from md_accountsubject jc2
             where jc2.stdcode = jc.stdcode
               and jc2.acctyear = jc.acctyear
               and jc2.sortorder > jc.sortorder);
  end if;

  --获取项目recid
  select count(1)
    into m_recordnum
    from md_project jc
   where jc.acctyear = p_year
     and jc.stdcode = p_xm_code;

  if (m_recordnum > 0) then
    select jc.recid
      into m_xmid
      from md_project jc
     where jc.acctyear = p_year
       and jc.stdcode = p_xm_code
       and not exists (select 1
              from md_project jc2
             where jc2.stdcode = jc.stdcode
               and jc2.acctyear = jc.acctyear
               and jc2.sortorder > jc.sortorder);
  end if;

  --获取功能recid
  select count(1)
    into m_recordnum
    from md_expendfuncclass jc
   where jc.acctyear = p_year
     and jc.stdcode = p_gn_code;

  if m_recordnum > 0 then
    select jc.recid
      into m_gnid
      from md_expendfuncclass jc
     where jc.acctyear = p_year
       and jc.stdcode = p_gn_code
       and not exists (select 1
              from md_expendfuncclass jc2
             where jc2.stdcode = jc.stdcode
               and jc2.acctyear = jc.acctyear
               and jc2.sortorder > jc.sortorder);
  end if;

  --获取经济recid
  select count(1)
    into m_recordnum
    from md_expendeconclass jc
   where jc.acctyear = p_year
     and jc.stdcode = p_jj_code;
  if m_recordnum > 0 then
    select jc.recid
      into m_jjid
      from md_expendeconclass jc
     where jc.acctyear = p_year
       and jc.stdcode = p_jj_code
       and not exists (select 1
              from md_expendeconclass jc2
             where jc2.stdcode = jc.stdcode
               and jc2.acctyear = jc.acctyear
               and jc2.sortorder > jc.sortorder);
  end if;
  --检查辅助组合表是否存在  
--  select count(1)
--    into m_recordnum
--    from gl_assistcomb t
--   where nvl(t.projectid, m_empty) = nvl(m_xmid, m_empty)
--     and nvl(t.expendfuncclassid, m_empty) = nvl(m_gnid, m_empty)
--     and nvl(t.expendeconclassid, m_empty) = nvl(m_jjid, m_empty);
--  if m_recordnum > 0 then
--    select t.recid
--      into m_asscombid
--      from gl_assistcomb t
--     where nvl(t.projectid, m_empty) = nvl(m_xmid, m_empty)
--       and nvl(t.expendfuncclassid, m_empty) = nvl(m_gnid, m_empty)
--       and nvl(t.expendeconclassid, m_empty) = nvl(m_jjid, m_empty);
--  elsif (m_xmid is not null or m_gnid is not null or m_jjid is not null) then
if (m_kmid is not null) then
	    --不存在则新建。最终获得组合表recid
	  if (m_xmid is not null or m_gnid is not null or m_jjid is not null) then
	    m_asscombid := sys_guid();
	    insert into gl_assistcomb (RECID, RECVER, ACCYEAR, PROJECTID, EXPENDFUNCCLASSID, EXPENDECONCLASSID) values (m_asscombid, 0, p_year, m_xmid, m_gnid, m_jjid);
	  end if;
	  --插入子表数据
	  insert into gl_voucheritem
	    (RECID, RECVER, VCHRID, ITEMORDER, SUBJECTID, DEBIT, CREDIT, ASSCOMBID)
	  values
	    (sys_guid(), 0, hextoraw(p_mrecid), p_itemorder, m_kmid, p_debit, p_credit, m_asscombid);
	  p_message := '200';
	  commit;
 
 end if;
  --异常处理
EXCEPTION
  WHEN OTHERS THEN
    p_message := '500:' || sqlerrm;
    rollback;
end;
