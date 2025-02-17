//gameComments, inventoryItemComments, gatheringComments, resources, address?

import { Game } from "./game";
import { Gathering } from "./gathering";
import { InventoryItem } from "./inventory-item";

export class User {

  id: number;
  username: string;
  password: string;
  enabled: boolean;
  firstName: string;
  lastName: string;
  role: string;
  email: string;
  imageUrl: string;
  biography: string;
  lastUpdate: string;
  gatheringsAttended: Gathering[];
  gatheringsHosted: Gathering[];
  inventoryItems: InventoryItem[];
  favoriteGames: Game[];

  constructor(
    id: number = 0 ,
    username: string = '',
    password: string = '',
    enabled: boolean = false,
    firstName: string = '',
    lastName: string = '',
    role: string = '',
    email: string = '',
    imageUrl: string = '',
    biography: string = '',
    lastUpdate: string = '',
    gatheringsAttended: Gathering[] = [],
    gatheringsHosted: Gathering[] = [],
    inventoryItems: InventoryItem[] = [],
    favoriteGames: Game[] = [],

  ){
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role  = role;
    this.email = email;
    this.imageUrl = imageUrl;
    this.biography = biography;
    this.lastUpdate = lastUpdate;
    this.gatheringsAttended = gatheringsAttended;
    this.gatheringsHosted = gatheringsHosted;
    this.inventoryItems = inventoryItems;
    this.favoriteGames = favoriteGames;
  }
}
