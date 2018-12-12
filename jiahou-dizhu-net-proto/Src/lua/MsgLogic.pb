
Ö(
MsgLogic.protoProtoNetMsgCode.protoMsgGate.proto"–
SdkController
uniqueId (	
nickName (	
headImg (	

ip (	
headType (
health (
sex (
selfRank (
robot	 (
watcher
 (

lv (
	extendStr (	"R
PosBrife
pos (
controllerUniqueId (	
online (
ready ("›
RoomInfo
roomId (	
roomName (	
roomerUniqueId (	,
controllers (2.ProtoNet.SdkController
	extendStr (	

roomStatus (%
	posBrifes (2.ProtoNet.PosBrife
leftMatchCount (

totalCount	 (6
dissolutionStatus
 (2.ProtoNet.DissolutionStatus
maxPlayerNum (
currentPlayerNum (

readyTimer (
gameId (
roomType (
	timesType (
yazhu (
watting (
from (

to (
serverExtend (	
fangkaJinbi (

chargeList (	
ext (	
clubId (	
tableNum (	
clubGameTypeId (	"ä
DissolutionStatus
dissolutionStatus (
reqUniqueId (	2
details (2!.ProtoNet.DissolutionStatusDetail
	leftTimer ("a
DissolutionStatusDetail
roomId (	
controllerUniqueId (	
agree (
pos ("0
ReqLogicLogin
account (	
ticket (	"9
ResLogicLogin(
result (2.ProtoNet.ResponseResult"ü
ReqCreateGameRoom
roomType (
times (
paramString (	
	timesType (
payType (
halfWayJoin (
maxPlayerNum (
yazhu (
fangkaJinbi	 (
minNeed
 (

pipeiParam (	
clubId (	
clubTableNum (	
clubGameTypeId (	"Q
ResCreateGameRoom(
result (2.ProtoNet.ResponseResult

readyTimer (")
ResNotifyGameRoomCreate
roomId (	"!
ReqJoinGameRoom
roomId (	"u
ResJoinGameRoom(
result (2.ProtoNet.ResponseResult$
roomInfo (2.ProtoNet.RoomInfo

readyTimer ("Å
ResNotifySomeOneJoinGameRoom
roomId (	+

controller (2.ProtoNet.SdkController$
posBrife (2.ProtoNet.PosBrife"
ReqExitGameRoom";
ResExitGameRoom(
result (2.ProtoNet.ResponseResult"[
ResNotifySomeOneExitGameRoom
roomId (	+

controller (2.ProtoNet.SdkController"
ReqGameReady
ready ("8
ResGameReady(
result (2.ProtoNet.ResponseResult"
ReqDissolutionGameRoom"B
ResDissolutionGameRoom(
result (2.ProtoNet.ResponseResult"ö
&ResNotifySomeOneReqDissolutionGameRoom
roomId (	+

controller (2.ProtoNet.SdkController$
posBrife (2.ProtoNet.PosBrife
timer (",
ReqAgreeDissolutionGameRoom
agree ("G
ResAgreeDissolutionGameRoom(
result (2.ProtoNet.ResponseResult"ù
)ResNotifySomeOneAggreeDissolutionGameRoom
roomId (	+

controller (2.ProtoNet.SdkController$
posBrife (2.ProtoNet.PosBrife
agree ("
ResNotifyGameRoomDissolution"
ResNotifyGameOver"
ReqGetRoomInfo"`
ResGetRoomInfo(
result (2.ProtoNet.ResponseResult$
roomInfo (2.ProtoNet.RoomInfo"ç
ResNotifySomeOneGameReady
roomId (	+

controller (2.ProtoNet.SdkController$
posBrife (2.ProtoNet.PosBrife
ready ("'
ReqChangeOnlineStatus
online ("A
ResChangeOnlineStatus(
result (2.ProtoNet.ResponseResult"ó
"ResNotifySomeOneChangeOnlineStatus
roomId (	+

controller (2.ProtoNet.SdkController$
posBrife (2.ProtoNet.PosBrife
online ("
ResNotifyBigCalculatePanel"
ReqCorrectTimer"Y
ResCorrectTimer(
result (2.ProtoNet.ResponseResult
leftDissolutionTimer ("(
ReqQuickChangeRoom

pipeiParam (	"û
ResQuickChangeRoom(
result (2.ProtoNet.ResponseResult
operate (
roomType (9
bestServerInfo (2!.ProtoNet.ServerInfoIpsSafeDetail
roomId (	7
logicDetails (2!.ProtoNet.ServerInfoIpsSafeDetail
gameId (
	timesType (
changeServer	 ("M
ResNotifySomeOneChangeRobot
isRobot (
uniqueId (	
pos ("ö
ResNotifyChangeToOldRoom9
bestServerInfo (2!.ProtoNet.ServerInfoIpsSafeDetail
roomId (	
gameId (
	timesType (
roomType ("

ReqSitDown
pos ("6

ResSitDown(
result (2.ProtoNet.ResponseResult"S
ResNotifySomeOneSitDown
pos (+

controller (2.ProtoNet.SdkController"

ReqStandUp"6

ResStandUp(
result (2.ProtoNet.ResponseResult"S
ResNotifySomeOneStandUp
pos (+

controller (2.ProtoNet.SdkController"F
ResNotifySomeOneEnterCharge
	uniqueIds (	
rechargeTime ("w
ResNotifyAccountsChange
uniqueId (	'
counts (2.ProtoNet.PlayerAccount
	extendStr (	
charge ("'
ResNotifyChargeOver
uniqueId (	".
ReqWatherList
length (
paixu ("j
ResWatherList(
result (2.ProtoNet.ResponseResult/
sdkControllers (2.ProtoNet.SdkController"
ReqOnceMore"U
ResOnceMore(
result (2.ProtoNet.ResponseResult
type (
roomId (	"[
ResNotifyOnceMore(
result (2.ProtoNet.ResponseResult
type (
roomId (	"
reqLogicServerTime"R
resLogicServerTime(
result (2.ProtoNet.ResponseResult

serverTime (B
com.version.protobuf.pb