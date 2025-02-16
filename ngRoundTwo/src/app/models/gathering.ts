
/*The gathering model will need to be padded out more
I believe that we need to make more of the other models such as address,
game, comment,host, end date and start date. Im not sure of how to implement the users
signing up or off of the event */
export class Gathering {
  id: number;
  name: string;
  description: string;
  fee: number;
  minParticipants: number;
  maxParticipants: number;
  imageUrl: string;
  enabled: boolean;

  constructor(
    id: number = 0,
  name: string = '',
  description: string = '',
  fee: number = 0,
  minParticipants: number = 2,
  maxParticipants: number = 2,
  imageUrl: string = '',
  enabled: boolean = false
  ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.fee = fee;
    this.minParticipants = minParticipants;
    this.maxParticipants = maxParticipants;
    this.imageUrl = imageUrl;
    this.enabled = enabled;
  }
}
