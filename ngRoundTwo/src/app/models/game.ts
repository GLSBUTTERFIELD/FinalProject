import { GameResource } from './game-resource';
import { Category } from "./category";

// gatherings, inventory items, favoritedUsers?
export class Game {
  id : number;
  name : string;
  description : string;
  minAge : number;
  websiteUrl: string;
  imageUrl : string;
  minPlayers : number;
  maxPlayers : number;
  categories : Category[];
  resources : GameResource[];
  // comments : GameComment[];

  constructor(
  id : number = 0,
  name : string = "",
  description : string = "",
  minAge : number = 0,
  websiteUrl: string = "",
  imageUrl : string = "",
  minPlayers : number = 0,
  maxPlayers : number = 0,
  categories : Category[] = [],
  resources : GameResource[] = []
  // comments : GameComment[] = []
  ){
    this.id =  id;
    this.name = name;
    this.description = description;
    this.minAge = minAge;
    this.websiteUrl = websiteUrl;
    this.imageUrl = imageUrl;
    this.minPlayers = minPlayers;
    this.maxPlayers = maxPlayers;
    this.categories = categories;
    this.resources = resources;
    // this.comments : GameComment[] = []
  }
}
