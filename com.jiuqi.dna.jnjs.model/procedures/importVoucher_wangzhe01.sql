  --插入单据主表
create or replace procedure importVoucher_wangzhe01
(
  p_recid      in varchar2,
  p_recver     in NUMBER,
  p_vchrnum    in int,
  p_acctyear   in int,
  p_acctperiod in int,
  p_orgcode    in varchar2,
  p_createdate in varchar2,
  p_message    out NVARCHAR2
) is
  m_recordnum NUMBER := 0;
begin
  select count(1) into m_recordnum from gl_voucher Where recid = hextoraw(p_recid);
  if (m_recordnum > 0) then
    p_message := '304';
    return;
  else
    insert into gl_voucher
      (RECID, RECVER, VCHRNUM, ACCTYEAR, ACCTPERIOD, UNITID, CREATEDATE, CREATETIME)
    values
      (p_recid, p_recver, p_vchrnum, p_acctyear, p_acctperiod, (select m.recid from MD_ORG m where m.stdcode = p_orgcode), to_date(p_createdate, 'yyyy-mm-dd'), sysdate);
    p_message := '200';
    commit;
  end if;
  --异常处理 
EXCEPTION
  WHEN OTHERS THEN
    p_message := '500:' || sqlerrm;
    rollback;
end;