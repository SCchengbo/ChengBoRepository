
Þ5
MsgGate.protoProtoNetMsgCode.proto"¥
AccountInfo
name (	
nick (	
uid (
sex (
phone (	
email (	
head (
headUrl (	

lv	 (
vip
 (
	ipaddress (	
address (	
inviter ('
counts (2.ProtoNet.PlayerAccount,
lastGameInfo (2.ProtoNet.LastGameInfo"¯
LastGameInfo
gameId (,
detail (2.ProtoNet.LastGameInfoDetail/
gate (2!.ProtoNet.ServerInfoIpsSafeDetail0
logic (2!.ProtoNet.ServerInfoIpsSafeDetail"B
LastGameInfoDetail
type (
roomId (	
status (".
GameZoneInfo
gameId (
isOpen ("Ÿ
ServerInfoIpsSafeDetail
serverId (

serverName (	
	playerNum (
isOpen (;
safeIpAndPortDetails (2.ProtoNet.SafeIpAndPortDetail"a
SafeIpAndPortDetail
safeIp (	
safePort (	

evironment (	
safeHttpPort (	"‚
GoldRoomServerInfos
roomId (	
roomName (	
roomerUniqueId (	
roomerNickName (	
roomerHeadImg (	
roomerHeadType (
roomType (
gameId (
	timesType	 (5

serverInfo
 (2!.ProtoNet.ServerInfoIpsSafeDetail"Û
OpenRoomReplaceRecordInfo
roomId (	
roomerUniqueId (	
roomerNickName (	

createTime (
gameId (
roomType (
	timesType (
	totalTime (5

serverInfo	 (2!.ProtoNet.ServerInfoIpsSafeDetail
totalNum
 (6
replaceRecordOver (2.ProtoNet.ReplaceRecordOver
	jsonParam (	
status ("a
ReplaceRecordOver
uniqueId (	
nick (	
headImg (	
head (
score ("…
	TimesInfo
	timesType (
	timesName (	
minNeed (
yazhu (
atype (
roomType (
	extendStr (	"D
ReqGateLogin
playerId (
ticket (	

evironment (	"8
ResGateLogin(
result (2.ProtoNet.ResponseResult"%
ReqGetAccountInfo
playerId ("i
ResGetAccountInfo(
result (2.ProtoNet.ResponseResult*
accountInfo (2.ProtoNet.AccountInfo"
ReqGetGameZoneInfoList"q
ResGetGameZoneInfoList(
result (2.ProtoNet.ResponseResult-
gameZoneInfos (2.ProtoNet.GameZoneInfo"8
ReqGetGameZoneDetail
gameId (
roomType ("²
ResGetGameZoneDetail(
result (2.ProtoNet.ResponseResult
gameId (7
logicDetails (2!.ProtoNet.ServerInfoIpsSafeDetail'

timesInfos (2.ProtoNet.TimesInfo"&
ReqJoinLogicGameRoom
roomId (	"°
ResJoinLogicGameRoom(
result (2.ProtoNet.ResponseResult9
bestServerInfo (2!.ProtoNet.ServerInfoIpsSafeDetail
gameId (
roomType (
	timesType ("_
ReqQuickJoinGameRoom
gameId (
roomType (
	timesType (

pipeiParam (	"Š
ResQuickJoinGameRoom(
result (2.ProtoNet.ResponseResult
operate (
roomType (9
bestServerInfo (2!.ProtoNet.ServerInfoIpsSafeDetail
roomId (	7
logicDetails (2!.ProtoNet.ServerInfoIpsSafeDetail
gameId (
	timesType ("E
ResNotifyBroadPlatMarquee
type (
msg (	
count ("U
ResNotifySelfDataChange*
accountInfo (2.ProtoNet.AccountInfo
charge ("%
ReqGetRoomBrifeList
gameId ("{
ResGetRoomBrifeList(
result (2.ProtoNet.ResponseResult:
goldRoomServerInfos (2.ProtoNet.GoldRoomServerInfos"
ReqCheckInMatch"¿
ResCheckInMatch(
result (2.ProtoNet.ResponseResult
inMatch (
randId (	
matchConfigId (
gameId (
selfRank (
from (

to (
taotai	 ("§
ReqOpenRoomReplace
roomType (
times (
paramString (	
	timesType (
halfWayJoin (
maxPlayerNum (
yazhu (
gameId ("˜
ResOpenRoomReplace(
result (2.ProtoNet.ResponseResult
roomId (	
roomType (
times (
paramString (	
	timesType (
halfWayJoin (
maxPlayerNum (
yazhu	 (
gameId
 (5

serverInfo (2!.ProtoNet.ServerInfoIpsSafeDetail".
ReqOpenRoomReplaceRecordList
status ("ª
ResOpenRoomReplaceRecord_List(
result (2.ProtoNet.ResponseResult2
infos (2#.ProtoNet.OpenRoomReplaceRecordInfo
daikaifangRecordNum (
status ("/
ReqDissolutionOpenRoomReplace
roomId (	"I
ResDissolutionOpenRoomReplace(
result (2.ProtoNet.ResponseResult"I
ReqGameRoomList
gameId (
	timesType (
fangkaJinbi ("a
ResGameRoomList(
result (2.ProtoNet.ResponseResult$
list (2.ProtoNet.GameRoomList" 
GameRoomList
roomId (	
paramString (	
yazhu (
halfWayJoin (
maxPlayerNum (
fangkaJinbi (
minNeed (

pipeiParam (	5

serverInfo	 (2!.ProtoNet.ServerInfoIpsSafeDetail
gameId
 (
currentPlayerNum (

roomStatus (
currentWatchPlayerNum (
roomType (
	timesType (,
roomRoleInfo (2.ProtoNet.RoomRoleInfo
tableNum (	"w
ReqClubGameRoomList
roomType (
gameId (
clubId (	
clubPipeiParam (	
clubGameTypeId (	"ó
ResClubGameRoomList(
result (2.ProtoNet.ResponseResult(
list (2.ProtoNet.ClubGameRoomList

clubCurNum (
clubTotalNumLimit (
onlineTotal (
clubId (	
	totalCard (
intro (	

playingNum	 ("¼
ClubGameRoomList
roomId (	
paramString (	
yazhu (
halfWayJoin (
maxPlayerNum (
fangkaJinbi (
minNeed (

pipeiParam (	5

serverInfo	 (2!.ProtoNet.ServerInfoIpsSafeDetail
gameId
 (
currentPlayerNum (

roomStatus (
currentWatchPlayerNum (
roomType (
	timesType (
tableNum (	,
roomRoleInfo (2.ProtoNet.RoomRoleInfo
roomerUniqueId (	"^
RoomRoleInfo
uniqueId (	
nickName (	
headImg (	
head (
pos ("Ø
ReqClubPipeiRoom
roomType (
times (
paramString (	
	timesType (
halfWayJoin (
maxPlayerNum (
yazhu (
gameId (
clubId	 (
playerId
 (
payType ("<
ResClubPipeiRoom(
result (2.ProtoNet.ResponseResult"
ReqExitClubPipeiRoom"@
ResExitClubPipeiRoom(
result (2.ProtoNet.ResponseResult"
ReqClubPipeiRoomStatus"´
ResClubPipeiRoomStatus(
result (2.ProtoNet.ResponseResult
gameId (
clubId (
times (
paramString (	
maxPlayerNum (
matchPlayerNum ("
ResNotifyClubPipeiSuc
clubId (	
roomId (	
roomType (
times (
paramString (	
	timesType (
halfWayJoin (
maxPlayerNum (
yazhu	 (
gameId
 (5

serverInfo (2!.ProtoNet.ServerInfoIpsSafeDetail""
 ResNotifyRefreshClubGameRoomList" 
ResNotifyRefreshClubRedBotNews"&
ResNotifyClubDismiss
clubId (	"(
ResNotifyClubKickedOut
clubId (	"-
ResNotifyClubChangeGameType
clubId (	B
com.version.protobuf.pb