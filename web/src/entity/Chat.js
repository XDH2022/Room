class Chat{
    sendUserId
    receiveId
    roomId
    ownerId
    content

    constructor(sendUserId, receiveId, roomId, ownerId, content) {
        this.sendUserId = sendUserId;
        this.receiveId = receiveId;
        this.roomId = roomId;
        this.ownerId = ownerId;
        this.content = content;
    }

    toJson(){
        return JSON.stringify(this)
    }
}
export {Chat}