export interface PrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  print(options: { content: string }): Promise<void>;
}
