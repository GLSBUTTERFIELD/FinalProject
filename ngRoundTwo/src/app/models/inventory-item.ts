import { User } from "./user";

export class InventoryItem {
  id: number;
  name: string;
  notes: string;
  available: boolean;
  imageUrl: string;
  enabled: boolean;
  createDate: string;
  lastUpdate: string;
  user: User;

  constructor(
    id: number = 0,
    user: User = new User(),
  name: string = '',
  notes: string = '',
  available: boolean = false,
  imageUrl: string = '',
  enabled: boolean = false,
  createDate: string = '',
  lastUpdate: string = '',
  ){
   this.id = id;
   this.user = user;
   this.name = name;
   this.notes = notes;
   this.available = available;
   this.imageUrl = imageUrl;
   this.enabled =  enabled;
   this.createDate = createDate;
   this.lastUpdate = lastUpdate;

  }
}
