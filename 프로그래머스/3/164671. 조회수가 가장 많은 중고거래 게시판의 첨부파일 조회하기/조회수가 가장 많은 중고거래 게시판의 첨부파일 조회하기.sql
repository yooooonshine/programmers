# 조회수가 가장 높은 중고거래 게시물에 대한 첨부파일 경로
with MAX_VIEW as (
    select max(VIEWS) as M
    from USED_GOODS_BOARD
), MAX_BOARD as (
    select BOARD_ID
    from USED_GOODS_BOARD
    where VIEWS = (select M from MAX_VIEW)
)

select CONCAT(CONCAT(CONCAT(CONCAT('/home/grep/src/', F.BOARD_ID), CONCAT('/', F.FILE_ID)), FILE_NAME), F.FILE_EXT) as FILE_PATH
from USED_GOODS_FILE as F
where F.BOARD_ID = (select BOARD_ID from MAX_BOARD)
order by FILE_ID desc;