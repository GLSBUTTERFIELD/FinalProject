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
  // categories : Category[];
  // resources : Resource[];
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
  // categories : Category[] = []
  // resources : Resource[] = []
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
    // this.categories : Category[] = []
    // this.resources : Resource[] = []
    // this.comments : GameComment[] = []
  }
}
