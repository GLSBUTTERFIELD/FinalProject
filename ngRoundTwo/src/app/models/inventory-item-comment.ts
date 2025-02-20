import { InventoryItem } from "./inventory-item";
import { User } from "./user";

export class InventoryItemComment {
id: number;
comment: string;
createDate: string;
lastUpdate: string;
enabled: boolean;
user: User | null;
inReplyTo: InventoryItemComment | null;
item: InventoryItem | null;

constructor(
  id: number = 0,
  comment: string = '',
  createDate: string = '',
  lastUpdate: string = '',
  enabled: boolean = false,
  user: (User | null) = null,
  inReplyTo: InventoryItemComment | null = null,
  item: (InventoryItem | null) = null,
){
this.id = id;
this.comment= comment;
this.createDate= createDate;
this.lastUpdate=lastUpdate;
this.enabled=enabled;
this.user=user;
this.inReplyTo = inReplyTo;
this.item = item;
}
}
