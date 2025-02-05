public String buildHtml(HttpServletRequest request, PfsBaseApplication appObj) {
String notice = "";
Hashtable<String,String> dataHash = new Hashtable<String,String>();
String agentId = ((String) request.getParameter("agentId")).toUpperCase().trim();
OsjSupervisorServer server = new OsjSupervisorServer();
if (agentId.length() != 5) {
return supervisorError(
getPageName(),
"Invalid solution number entered. Solution numbers must be 5 characters long");
}

try {
if (!server.isOnRemoteRepsTable(agentId)) {
return supervisorError(
getPageName(),
"Agent entered does not appear to be a remote representative");

}
} catch (Exception e) {
return supervisorError(getPageName(), "Error checking agent id");
}

String uplineInfoBox = buildRemoteRepUplineInfoBox(agentId);
if (uplineInfoBox == null) {
return supervisorError(
getPageName(),
"Error finding upline info for solution number entered");
}
dataHash.put("uplineInfoBox", uplineInfoBox);

String trackingStatus = "";

try{
trackingStatus = server.getRemoteRepTrackingStatus(agentId);
}catch (Exception e){
trackingStatus = "Unable to load tracking status for " + agentId;
}

dataHash.put("trackingStatus", trackingStatus);


dataHash.put("remoteRepVisitInfo", buildRemoteRepVisitInfo(agentId));


dataHash.put("remoteRepVisitHistory", buildRemoteRepVisitHistory(agentId));
dataHash.put("notice", notice);

return returnPage(OsjConstants.SUPERVISOR_APPNAME, getPageName(), dataHash);

}
