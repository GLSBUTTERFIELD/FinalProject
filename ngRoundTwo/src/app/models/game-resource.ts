export class GameResource {
  id: number;
  name: string;
  description: string;
  createDate: string;
  lastUpdate: string;
  enabled: boolean;
  resourceUrl: string;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    createDate: string = '',
    lastUpdate: string = '',
    enabled: boolean = false,
    resourceUrl: string = '',
  ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.createDate = createDate;
    this.lastUpdate = lastUpdate;
    this.enabled = enabled;
    this.resourceUrl = resourceUrl;

  }
}
