# Changelog


## [unreleased]

### Changed
- Rename android package android-pubsub to minipubsub

### Removed
- Tag removed

## [0.1.0] - 2024-04-24

### Added

- Envelop struct added. Envelop has a Message
- EnvelopeHolder struct added. Holds Envelope struct internally.
- Add matchTag to Receivable
- Add setReceivingRule to Receivable
- Add setBasePublishingTag to ReceivablePublisher

### Changed

- Rename package com.pj.core to com.pj.pubsub
- Rename module name androidBridgeCore to android-pubsub
- Tags are now synced between unity and native.

### Removed

- Delete MessageCollector.
- Delete MessageHandler.

## [0.0.1] - 2024-02-29

### Added

- Initial release