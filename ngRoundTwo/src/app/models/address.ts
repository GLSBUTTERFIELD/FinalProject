

export class Address {
  id: number;
  name: string;
  street: string;
  stateAbbr: string;
  zip: string;
  city: string;

  constructor(
    id: number = 0,
    name: string = '',
    street: string = '',
    stateAbbr: string = '',
    zip: string = '',
    city: string = ''
  ){
    this.id = id;
    this.name = name;
    this.street = street;
    this.stateAbbr = stateAbbr;
    this.zip = zip;
    this.city = city;
  }

}
