import { User } from "./user";

export class GameResource {
  id: number;
  name: string;
  description: string;
  createDate: string;
  lastUpdate: string;
  enabled: boolean;
  resourceUrl: string;
  user: User;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    createDate: string = '',
    lastUpdate: string = '',
    enabled: boolean = false,
    resourceUrl: string = '',
    user: User = new User(),
  ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.createDate = createDate;
    this.lastUpdate = lastUpdate;
    this.enabled = enabled;
    this.resourceUrl = resourceUrl;
    this.user = user;
  }
}
