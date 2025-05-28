declare module 'pikaday' {
    interface PikadayOptions {
      field: HTMLElement;
      format?: string;
      onSelect?: (date: Date) => void;
      toString?: (date: Date, format?: string) => string;
      // Agrega más opciones según sea necesario
    }
  
    export default class Pikaday {
      constructor(options: PikadayOptions);
      destroy(): void;
      setDate(date: Date | string, triggerOnSelect?: boolean): void;
      getDate(): Date | null;
      // Agrega más métodos según sea necesario
    }
}  