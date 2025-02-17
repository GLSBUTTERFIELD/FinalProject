import { User } from "./user";

export class InventoryItemComment {
id: number;
comment: string;
createDate: string;
lastUpdate: string;
enabled: boolean;
user: User;
inReplyTo: InventoryItemComment;

constructor(
  id: number = 0,
  comment: string = '',
  createDate: string = '',
  lastUpdate: string = '',
  enabled: boolean = false,
  user: User = new User(),
  inReplyTo: InventoryItemComment = new InventoryItemComment(),
){
this.id = id;
this.comment= comment;
this.createDate= createDate;
this.lastUpdate=lastUpdate;
this.enabled=enabled;
this.user=user;
this.inReplyTo = inReplyTo;
}
}
